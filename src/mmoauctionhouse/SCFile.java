/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmoauctionhouse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Neil
 */
public class SCFile implements IFile {

    private final String delimiter = ";";
    private String writeFileName = "";
    private String readFileName = "";
    
    public SCFile()
    {
       
    }

    @Override
    public String [][] read() {
        File newFile = new File(readFileName);
        Scanner in = null;
        try {
            in = new Scanner(newFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SCFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(readFileName);
        String [][] returnString;
        int numRows = 0;
        int numColumns = 0;
        String line = "";
        while (in.hasNextLine())
        {
            line = in.nextLine();
            numRows++;
            //finding largest number of columns and using for init
            int thisNumColumns = line.split(delimiter).length;
            if (thisNumColumns > numColumns)
                numColumns = thisNumColumns;
        }
        try {
            in = new Scanner(newFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SCFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        returnString = new String[numRows][numColumns];
        for (int i = 0; i < numRows;i++ )
        {
            line = in.nextLine();
            returnString[i] = line.split(delimiter);
        }
        return returnString;
    }

    @Override
    public void write(String[][] write) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(writeFileName, true);
            int largestColumn = 0;
            for (int i = 0; i < write.length;i++)
            {
                int numColumn = write[i].length;
                if (numColumn > largestColumn)
                    largestColumn = numColumn;
            }
            for (int i = 0; i < write.length; i++)
            {
                for (int j = 0; j < largestColumn; j++)
                {
                    if (j < (largestColumn - 1))
                        fw.write(write[i][j] + delimiter);
                    else
                        fw.write(write[i][j]);
                }
                fw.write("\r\n");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "File not found!");
        }
        finally
        {
            try {
                fw.close();
            } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Could not close FileWriter");
            }
        }
    }

    @Override
    public void get(String filepath) {
        this.readFileName = filepath;
    }

    @Override
    public void put(String filepath) {
        this.writeFileName = filepath;
    }

    @Override
    public String getDelimiter() {
        return delimiter;
    }
    
}
