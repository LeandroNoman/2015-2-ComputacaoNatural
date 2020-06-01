package genetictree;

import compnaturaltp1.MyRandom;
import compnaturaltp1.Point;
import genetictree.functions.*;
import genetictree.terminals.ConstantTerminal;
import genetictree.terminals.VariableTerminal;
import java.util.ArrayList;

public class ArithmeticTree {
    
    private TreeNode rootNode;
    private final MyRandom rand;
    private final int dimension;
    private int numberNodes = 0;
    private final int maxSize;
    private double fitnessValue = Double.POSITIVE_INFINITY;

    public ArithmeticTree(int dimension, int maxSize) {
        this.dimension = dimension;
        this.maxSize = maxSize;
        rand = new MyRandom();
    }
    
    public void createNewGrow(int treeSize) {
        if(treeSize > maxSize) {
            treeSize = maxSize;
        }
        rootNode = createRandomTree(treeSize, null, true);
    }
    
    public void createNewFull(int treeSize) {
        if(treeSize > maxSize) {
            treeSize = maxSize;
        }
        rootNode = createRandomTree(treeSize, null, false);
    }
    
    //Retorna o fitness value da raiz
    public double evaluate(Point p) {
        return rootNode.evaluate(p);
    }
    
    //Calcula o fitness do individuo para um conjunto de pontos e guarda o valor
    public double calculateFitness(ArrayList<Point> points) {
        double singleError, cumulativeError;
        
        cumulativeError = 0.0;
        for(Point p : points) {
            singleError = evaluate(p);
            //Erro = valorDaEquacao - Y
            singleError -= p.getValue(dimension - 1);
            //Transforma o erro em erro absoluto
            singleError = java.lang.Math.abs(singleError);
            cumulativeError += singleError;
        }
        
        fitnessValue = cumulativeError;
        
        return cumulativeError;
    }
    
    //Apenas retorna o fitness calculado pela funcao calculateFitness
    public double fitness() {
        return fitnessValue;
    }
    
    //Realiza uma mutacao simples em um no
    //Essa mutacao apenas troca o no por um de mesma funcao
    public void mutation() {
        int chosenNode = rand.nextInt(numberNodes);
        int chosenNodeType;
        TreeNode mutated;
        
        mutated = rootNode.getNode(chosenNode);
        chosenNodeType = mutated.getNodeType();

        //Muda o node de acordo com seu tipo
        if(mutated.isFunction()) {
            FunctionNode fn = (FunctionNode) mutated;
            TreeNode lc = fn.getLeftChildren();
            TreeNode rc = fn.getRightChildren();
            TreeNode father = fn.getFather();
            int fatherIndex = fn.getFatherIndex();
            //Loop para ter certeza de que a mutacao mudou a arvore
            do {
                mutated = createRandomFunction(mutated.getIndex());
            } while(mutated.getNodeType() == chosenNodeType);
            ((FunctionNode)mutated).setChildren(lc, rc);
            mutated.setFather(father, fatherIndex);
            //Liga os filhos com o novo pai mutado
            lc.setFather(mutated, 0);
            rc.setFather(mutated, 1);
            //Liga o pai antigo com o filho mutado
            if(father != null) {
                father.setChildren(fatherIndex, mutated);
            }
            //Se o pai nao existir, ele e o no root
            else {
                rootNode = mutated;
            }
        }
        else if(mutated.isTerminal()) {
            TreeNode father = mutated.getFather();
            int fatherIndex = mutated.getFatherIndex();
            //Loop para ter certeza de que a mutacao mudou a arvore
            do {
                mutated = createRandomTerminal(mutated.getIndex());
            } while(mutated.getNodeType() == chosenNodeType &&
                    mutated.getNodeType() != TreeNode.CONSTANT);
            mutated.setFather(father, fatherIndex);
            //Liga o pai antigo com o filho mutado
            if(father != null) {
                father.setChildren(fatherIndex, mutated);
            }//Se o pai nao existir, ele e o no root
            else {
                rootNode = mutated;
            }
        }
    }
    
    //Realiza o crossover com outra arvore
    //O crossover muda a estrutura das arvores, nao cria arvores novas
    public void crossOver(ArithmeticTree crossTree) {
        int chosenNode = rand.nextInt(numberNodes);
        int givenNodes, receivedNodes, givenFatherIndex, maxSubtreeDepth;
        TreeNode given, received, givenFather;
        
        //Recupera uma subarvore que sera trocada com a outra arvore
        given = rootNode.getNode(chosenNode);
        givenFather = given.getFather();
        givenNodes = given.getNodesQuantity();
        givenFatherIndex = given.getFatherIndex();
        //Calcula a maior subarvore que pode ser recebida pelo crossover
        maxSubtreeDepth = given.getNodeDepth();
        maxSubtreeDepth = maxSize - maxSubtreeDepth;
        maxSubtreeDepth++;
        //Envia a subarvore para a outra arvore e recebe a subarvore da outra arvore
        received = crossTree.exchangeGeneticMaterial(given, maxSubtreeDepth);
        
        //Arruma os pais e filhos da subarvore recebida
        received.setFather(givenFather, givenFatherIndex);
        receivedNodes = received.getNodesQuantity();
        numberNodes = numberNodes + receivedNodes - givenNodes;
        if(givenFather != null) {
            givenFather.setChildren(givenFatherIndex, received);
        }
        else {
            rootNode = received;
            received.setFather(null, 0);
        }
        
        rootNode.updateIndex();
    }
    
    //Funcao chamada por uma arvore, passando sua subarvore a ser trocada
    //para a realizacao do crossover
    private TreeNode exchangeGeneticMaterial(TreeNode received, int maxSubtreeDepth) {
        int chosenNode, givenNodes, receivedNodes, givenFatherIndex;
        int subTreeDepth, givenDepth, receivedDepth;
        boolean sizeSafe = false;
        TreeNode given = null;
        
        
        receivedDepth = received.getSubTreeDepth();
        while(!sizeSafe) {
            chosenNode = rand.nextInt(numberNodes);
            //Recupera uma subarvore que sera trocada com a outra arvore
            given = rootNode.getNode(chosenNode);
            //Verifica se com a substituicao das arvores, os tamanhos sao respeitados
            //Verifica se essa arvore nao vai ultrapassar o tamanho maximo
            givenDepth = given.getNodeDepth();
            if(givenDepth + receivedDepth - 1 <= maxSize) {
                sizeSafe = true;
            }
            else {
                sizeSafe = false;
            }
            //Verifica se a primeira arvore do crossover nao vai ultrapassar o tamanho maximo
            subTreeDepth = given.getSubTreeDepth();
            if(sizeSafe == true && subTreeDepth <= maxSubtreeDepth) {
                sizeSafe = true;
            }
            else {
                sizeSafe = false;
            }
        }
        givenNodes = given.getNodesQuantity();
        givenFatherIndex = given.getFatherIndex();
        receivedNodes = received.getNodesQuantity();
        //Arruma os pais e filhos da subarvore recebida
        received.setFather(given.getFather(), givenFatherIndex);
        numberNodes = numberNodes - givenNodes + receivedNodes;
        if(given.getFather() != null) {
            given.getFather().setChildren(givenFatherIndex, received);
        }
        else {
            rootNode = received;
            received.setFather(null, 0);
        }
        
        rootNode.updateIndex();
        
        return given;
    }

    @Override
    public String toString() {
        return "y = " + rootNode.toString();
    }
    
    public void printIndex() {
        rootNode.printIndex();
    }
    
    //Retorna um clone completo dessa arvore
    public ArithmeticTree deepClone() {
        ArithmeticTree at = new ArithmeticTree(dimension, maxSize);
        
        at.numberNodes = numberNodes;
        at.fitnessValue = fitnessValue;
        //Copia cada no para a nova arvore
        at.rootNode = recursiveCloning(rootNode, null, 0);
        
        return at;
    }
    
    //Retorna a subarvore clonada de root
    private TreeNode recursiveCloning(TreeNode root, TreeNode father, int fatherIndex) {
        int i = 0;
        
        TreeNode tn = root.copy();
        tn.setFather(father, fatherIndex);
        //Clona todos os filhos, se houverem filhos
        if(root.getChildren() != null) {
            for(TreeNode children : root.getChildren()) {
                tn.setChildren(i, recursiveCloning(children, tn, i));
                i++;
            }
        }
        return tn;
    }

    //Cira uma subarvore com tamanho maxSize, assinalando fatherNode como pai
    //usando a tecnica full(se grow = false) ou grow(se grow = true)
    private TreeNode createRandomTree(int maxSize, TreeNode fatherNode, boolean grow) {
        int select;
        
        //Se chegou em uma folha, gera um terminal
        if(maxSize == 0) {
            numberNodes++;
            return createRandomTerminal(numberNodes - 1);
        }
        
        //Apenas cria um terminal antes de atingir o tamanho maximo da arvore
        //se estiver usando a tecnica grow
        if(grow) {
            select = rand.nextInt(4);
            //Gera um terminal com chance 1/4
            if(select == 0) {
                numberNodes++;
                return createRandomTerminal(numberNodes - 1);
            }
        }
        //Gera uma funcao com chance 3/4
        FunctionNode fn;
        TreeNode left, right;

        fn = createRandomFunction(numberNodes);
        numberNodes++;
        left = createRandomTree(maxSize - 1, fatherNode, grow);
        right = createRandomTree(maxSize - 1, fatherNode, grow);
        fn.setChildren(left, right);
        left.setFather(fn, 0);
        right.setFather(fn, 1);

        return fn;
    }
    
    private TerminalNode createRandomTerminal(int index) {
        TerminalNode tn;
        
        //Cria um terminal constante
        if(rand.nextBool()) {
            tn = new ConstantTerminal(createRandomDouble(), index);
        }
        //Cria um terminal variavel
        else {
            tn = new VariableTerminal(rand.nextInt(dimension - 1), index);
        }
        
        return tn;
    }
    
    private FunctionNode createRandomFunction(int index) {
        FunctionNode fn = null;
        int select = rand.nextInt(4);
        
        switch(select) {
            case 0:
                fn = new AddFunction(null, null, index);
                break;
            case 1:
                fn = new SubFunction(null, null, index);
                break;
            case 2:
                fn = new MultFunction(null, null, index);
                break;
            case 3:
                fn = new DivFunction(null, null, index);
                break;
        }
        
        return fn;
    }
    
    private double createRandomDouble() {
        return rand.nextDouble() * (rand.nextInt(21) - 10);
    }
    
}
