/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunwarprabin.notepad.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author OWNER
 */
public class Files {

    public static String reader(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                content.append(line + "\t\n");
            }
            reader.close();
            return content.toString();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return null;
    }

    public static void writer(String file, String text) {
        try {
           
            
            FileWriter writer = new FileWriter(file);
            writer.write(text);
            writer.close();
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }
}
