package compnaturaltp1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    
    private BufferedWriter fileWriter;
    private String fileName;
    
    public void createNewLog(String fileName) {
        this.fileName = fileName;
        try {
            fileWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException ex) {
            System.out.println("Erro ao criar arquivo de log.");
            return;
        }
    }
    
    public void closeLog() {
        if(fileWriter != null) {
            try {
                fileWriter.close();
            } catch (IOException ex) {
                System.out.println("Erro ao fechar o arquivo de log");
            }
        }
    }
    
    public void log(String line) {
        if(fileWriter == null) {
            System.out.println("Arquivo de log nao inicializado");
            return;
        }
        
        try {
            fileWriter.write(line);
            fileWriter.newLine();
        } catch (IOException ex) {
            System.out.println("Erro ao escrever no arquivo de log");
        }
    }
    
}
