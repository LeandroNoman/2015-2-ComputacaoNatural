package genetictree;

import compnaturaltp1.Point;

public abstract class TreeNode {
    
    //Tipos de nos que a funcao getNodeType pode retornar
    public static final int CONSTANT = -1;
    public static final int ADDFUNCTION = 1;
    public static final int SUBFUNCTION = 2;
    public static final int MULTFUNCION = 3;
    public static final int DIVFUNCTION = 4;
    
    //Indica se o no e uma funcao ou um terminal
    private final boolean function;
    private int fatherIndex;
    private TreeNode father;
    protected TreeNode[] children;
    
    //Indica o indice desse no na arvore (preorder)
    protected int index;
    
    public TreeNode(int index, boolean function) {
        this.index = index;
        this.function = function;
    }
    
    //Funcao recursiva para avaliar o fitness da arvore para o ponto p
    public abstract double evaluate(Point p);
    
    //Retorna o no da arvore com indice i
    public abstract TreeNode getNode(int i);
    
    //Retorna o tipo do no
    public abstract int getNodeType();
    
    //Atualiza os indices da arvore
    public abstract void updateIndex();
    protected abstract void updateIndex(IndexCounter ic);
    
    //Retorna uma copia do no
    public abstract TreeNode copy();
    
    //Retorna a quantidade de nos a partir desse no
    public abstract int getNodesQuantity();
    
    public abstract void printIndex();
    
    public abstract void setChildren(int childrenOrder, TreeNode children);
    
    //Retorna a profundidade do no na arvore ( de 0 a MAXSIZE )
    public int getNodeDepth() {
        int depth = 0;
        TreeNode tn = this;
        
        while(tn.getFather() != null) {
            tn = tn.getFather();
            depth++;
        }
        
        return depth;
    }
    
    //Retorna a profundidade da subarvore a partir desse no ( de 1 a MAXSIZE + 1)
    public int getSubTreeDepth() {
        int deeper = 0;
        
        if(children == null) {
            return deeper + 1;
        }
        
        for(TreeNode tn : children) {
            int childDepth = tn.getSubTreeDepth();
            if(childDepth > deeper) {
                deeper = childDepth;
            }
        }
        
        return deeper + 1;
    }
    
    public boolean isFunction() {
        return function;
    }
    
    public boolean isTerminal() {
        return !function;
    }
    
    public int getIndex() {
        return index;
    }
    
    public TreeNode[] getChildren() {
        return children;
    }
    
    public void setFather(TreeNode father, int fatherIndex) {
        this.father = father;
        this.fatherIndex = fatherIndex;
    }
    
    public TreeNode getFather() {
        return father;
    }
    
    public int getFatherIndex() {
        return fatherIndex;
    }
    
}
