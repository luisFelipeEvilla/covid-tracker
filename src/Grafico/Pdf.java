/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafico;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import covid.tracker.Vertice;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.itextpdf.text.pdf.PdfPTable;
import covid.tracker.Arista;

public class Pdf {

    Vertice v;

    public Pdf(Vertice v) {
        this.v = v;
    }

    public void generarMatriz() {
        Document documento = new Document();

        try {
            PdfWriter.getInstance(documento, new FileOutputStream("Matriz del grafo.pdf"));
            documento.open();
            documento.addTitle("Matriz");
            //  documento.addSubject("Using iText (usando iText)");
            //documento.addKeywords("Java, PDF, iText");
            documento.addAuthor("Juan Pablo Prada , Luis Felpe Evilla , Brancys");
            // documento.addCreator("Código Xules");

            PdfPTable tabla = new PdfPTable(v.cantidadDeVertices(v) + 1);
              Vertice v2 = v;
            PdfPCell c = new PdfPCell(new Paragraph(""));
            tabla.addCell(c);
            documento.add(tabla);
            documento.close();
        } catch (DocumentException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean[] falta(Vertice v) {
        boolean [] arreglo = new boolean[v.cantidadDeVertices(v)]; 
        System.out.println(v.cantidadDeVertices(v));
            Arista a = v.getAristas();
            int c = 0;
           for (int i = 0; i < arreglo.length; i++) {
               if(i==a.getId()&&a!=null){
                   arreglo[i]=true;
                   a=a.getLink();
               }
            
        }
        return arreglo;
    }

}
