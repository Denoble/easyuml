package org.uml.visual.dialogs;

import java.util.Map;
import org.uml.model.ClassComponent;
import org.uml.model.ClassDiagram;
import org.uml.model.ComponentBase;
import org.uml.model.PackageComponent;
import org.uml.visual.widgets.ClassDiagramScene;

/**
 *
 * @author Ilija
 */
public class PackageDialog extends javax.swing.JDialog {

    /**
     * Creates new form AddClassDialog
     */
    private ComponentBase comp;
    private ClassDiagram classDiagram;

    public PackageDialog(java.awt.Frame parent, boolean modal, ComponentBase comp, ClassDiagram cd) {
        super(parent, modal);
        initComponents();
        this.comp = comp;
        txtPackageName.setText("");
        this.classDiagram = cd;
        if (comp.getParentPackage() != null) {
            txtPackageName.setText(comp.getParentPackage().getName());
        }
    }

    public PackageDialog(java.awt.Frame parent, ClassDiagramScene scene, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtPackageName = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(PackageDialog.class, "PackageDialog.title")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PackageDialog.class, "PackageDialog.jLabel1.text")); // NOI18N

        txtPackageName.setText(org.openide.util.NbBundle.getMessage(PackageDialog.class, "PackageDialog.txtPackageName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnOk, org.openide.util.NbBundle.getMessage(PackageDialog.class, "PackageDialog.btnOk.text")); // NOI18N
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(cancelButton, org.openide.util.NbBundle.getMessage(PackageDialog.class, "PackageDialog.cancelButton.text")); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPackageName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPackageName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        System.out.println("INSTANCA COMPA JE TIPA: "+comp.getClass().getName());
        //first to remove info from package that it contains component
        PackageComponent p = comp.getParentPackage();
        if (p != null && p.getComponents().containsKey(comp.getName())) {
            p.removeComponent(comp);
        }
        //now to see if that package is empty (in that case, we have to remove it from ClassDiagram instance)
        if (p != null && p.getComponents().isEmpty()) {
            classDiagram.removePackage(p);
            p = null;
        }

        //now we set information about package on Component's instance
        String pack = txtPackageName.getText().trim();
        if (classDiagram.getPackages().containsKey(pack)) {
            PackageComponent packageComp = classDiagram.getPackages().get(pack);
            comp.setParentPackage(packageComp);
            packageComp.addComponent(comp);
        } else {
            PackageComponent packComp = new PackageComponent(pack);
            classDiagram.addPackage(packComp);
            comp.setParentPackage(packComp);
            System.out.println("COMP NAME: " + comp.getName());
            packComp.addComponent(comp);
        }

        System.out.println("Test to see all packages on classDiagram");

        for (Map.Entry<String, PackageComponent> entry : classDiagram.getPackages().entrySet()) {
            String name = entry.getKey();
            PackageComponent value = entry.getValue();
            value.printAllComponentsOnStdOut();
        }

        System.out.println("-------------------------------------------------------------------");


//        for (ClassDiagramComponent cdc : classDiagram.getComponents()){
//            System.out.println("Name: "+cdc.getName()+"\t Package: "+ cdc.getParentPackage().getName());
//        }
        for (Map.Entry<String, ComponentBase> entry : classDiagram.getComponents().entrySet()) {
            String name = entry.getKey();
            ComponentBase cdc = entry.getValue();
            if (cdc.getParentPackage() != null) {
                System.out.println("Name: " + cdc.getName() + "\t Package: " + cdc.getParentPackage().getName());
            }

        }
//        System.out.println("////////////////////////////////////////////////////////////");
//        System.out.println("Now for packages");
//        for (Map.Entry<String, PackageComponent> entry : classDiagram.getPackages().entrySet()) {
//            String name = entry.getKey();
//            PackageComponent packageComponent = entry.getValue();
//            String components = "";
//            
//            
//            System.out.println("Name: "+name+" \t Components: " );
//        }

//        comp.setParentPackage(new PackageComponent(pack));
        this.dispose();
    }//GEN-LAST:event_btnOkActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtPackageName;
    // End of variables declaration//GEN-END:variables
}
