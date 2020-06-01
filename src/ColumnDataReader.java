package compnaturaltp1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ColumnDataReader {
    
    private final String fileName;

    public ColumnDataReader(String fileName) {
        this.fileName = fileName;
    }
    
    /*
     * Retorna a lista de pontos do arquivo ou null se algum erro ocorrer.
     * O arquivo deve estar formatado com colunas de double, de mesmo tamanho cada linha.
     */
    ArrayList<Point> readData() {
        BufferedReader reader;
        String line;
        String[] stringValues;
        ArrayList<Point> data = new ArrayList<Point>();
        Point point;
        
        int dataDimension, pointsRead;
        double dValue;
        
        dataDimension = 0;
        pointsRead = 0;
        
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo " + fileName + " nao encontrado.");
            return null;
        }

        try {
            line = reader.readLine();
        } catch (IOException ex) {
            System.out.println("Erro ao ler o arquivo.");
            return null;
        }
        
        //Loop para procurar a dimensao dos dados do arquivo
        while(dataDimension == 0) {
            if(line == null) {
                System.out.println("Arquivo mal formatado.");
                return null;
            }
            dataDimension = fileDimention(line);
            //Le a proxima linha apenas se a dimensao nao foi achada ainda
            if(dataDimension == 0) {
                try {
                    line = reader.readLine();
                } catch (IOException ex) {
                    System.out.println("Erro ao ler o arquivo.");
                    return null;
                }
            }
        }
        
        if(dataDimension < 2) {
            System.out.println("Arquivo mal formatado. Dimensao dos dados menor que 2?");
            return null;
        }
        
        //Loop para ler cada linha do arquivo
        while(line != null) {
            pointsRead = 0;
            point = new Point(dataDimension);
            stringValues = line.split("\\s");
           
            //Loop para ler os pontos de uma linha
            for (int i = 0; i < stringValues.length; i++) {
                try {
                    dValue = Double.parseDouble(stringValues[i]);
                    point.setValue(pointsRead, dValue);
                    pointsRead++;
                } catch(NumberFormatException ex) {}
            }
            
            //So coloca o ponto na lista se foram lidos a quantidade certa de dados
            if(pointsRead == dataDimension) {
                data.add(point);
            }
            
            try {
                line = reader.readLine();
            } catch (IOException ex) {
                System.out.println("Erro ao ler o arquivo.");
                return null;
            }
        }
        
        return data;
    }
    
    //Descobre a dimensao dos dados do arquivo baseado em uma linha do arquivo
    private int fileDimention(String line) {
        String[] values;
        int pointsRead = 0;
        
        values = line.split("\\s");
        
        for(String v : values) {
            try {
                Double.valueOf(v);
                pointsRead++;
            } catch(NumberFormatException ex) { }
        }
        
        return pointsRead;
    }
}
