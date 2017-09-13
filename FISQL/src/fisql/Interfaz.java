package fisql;

import AnalizadorSQL.ParseException;
import AnalizadorSQL.parserSQL;
import USQL.Nodo;
import USQL.RecorridoSQL;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import BaseDeDatos.*;

public class Interfaz extends javax.swing.JFrame {
    
    public Interfaz() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textCodigo = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        textSalida = new javax.swing.JTextArea();
        btnAbrir = new javax.swing.JButton();
        btnEjecutar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textCodigo.setColumns(20);
        textCodigo.setRows(5);
        jScrollPane1.setViewportView(textCodigo);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 750, 220));

        textSalida.setColumns(20);
        textSalida.setRows(5);
        jScrollPane2.setViewportView(textSalida);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 750, 220));

        btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/open.jpg"))); // NOI18N
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });
        getContentPane().add(btnAbrir, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 70, 60));

        btnEjecutar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/j.png"))); // NOI18N
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEjecutar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 70, 60));

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/less.jpg"))); // NOI18N
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 70, 60));

        jLabel2.setFont(new java.awt.Font("TlwgTypewriter", 3, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 255));
        jLabel2.setText("FISQL");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 150, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/maze.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 820, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        try {
            JFileChooser file = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("usql", "usql");
            file.setFileFilter(filtro);
            file.showOpenDialog(this);
            File abre = file.getSelectedFile();
            String texto = "";
            String aux = "";
            if (abre != null) {
                FileReader archivos = new FileReader(abre);
                
                try (BufferedReader lee = new BufferedReader(archivos)) {
                    while ((aux = lee.readLine()) != null) {
                        texto += aux + "\n";
                    }
                }
            }
            textCodigo.setText(texto);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        BaseDeDatos.listaImprimir.clear();
        
        Nodo nodo = null;
        try {
            nodo = parserSQL.compilar(textCodigo.getText());
            RecorridoSQL r = new RecorridoSQL();
            r.Recorrido(nodo);
        } catch (ParseException | CloneNotSupportedException ex) {
            System.out.println("Error al ejecutar: " + ex);
        }
        
        for (int i = 0; i < BaseDeDatos.listaImprimir.size(); i++) {
            String v = (String) BaseDeDatos.listaImprimir.get(i);
            textSalida.append(BaseDeDatos.getFechaActual() + " " + BaseDeDatos.getHoraActual() + " " + BaseDeDatos.usuarioActual);
            textSalida.append(" " + v + "\n");
        }
//       textSalida.append("\n* * * * * * * * * * * * * * * * LOG * * * * * * * * * * * * * * * * * * \n");
//        for (int i = 0; i < Errores.erroresSQL.size(); i++) {
//            String v = (String) Errores.erroresSQL.get(i);
//            textSalida.append(v + "\n");
//        }

        FISQL.imprimirDatos();
    }//GEN-LAST:event_btnEjecutarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        BaseDeDatos.crearArchivos();
        this.setVisible(false);
        System.exit(0);
    }//GEN-LAST:event_btnCerrarActionPerformed
    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new Interfaz().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextArea textCodigo;
    public javax.swing.JTextArea textSalida;
    // End of variables declaration//GEN-END:variables
}
