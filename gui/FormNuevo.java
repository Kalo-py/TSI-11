package tsi.rp.s1.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tsi.rp.s1.modelo.Operaciones;
import tsi.rp.s1.modelo.Persona;

@SuppressWarnings("serial")
public class FormNuevo extends JFrame{
	private JPanel pnlPrincipal;
	private JLabel lblNroCi, lblNombre, lblApellido, lblEmail, lblFechaNac;
	private JTextField txtNroCi, txtNombre, txtApellido, txtEmail, txtFechaNac;
	private JButton btnAceptar;
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	public FormNuevo() {
		super("Registrar Persona");
		setSize(400,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		pnlPrincipal = new JPanel(new GridBagLayout());
		lblNroCi = new JLabel("Nro CI:");
		lblNombre = new JLabel("Nombre:");
		lblApellido = new JLabel("Apellido:");
		lblEmail = new JLabel("Email:");
		lblFechaNac = new JLabel("FechNac:");
		
		txtNroCi = new JTextField(12);
		txtNombre = new JTextField(12);
		txtApellido = new JTextField(12);
		txtEmail = new JTextField(12);
		txtFechaNac = new JTextField(12);
		
		btnAceptar = new JButton("Aceptar");

		Insets espacios = new Insets(2,2,6,2);
		gbc.insets = espacios;
		gbc.anchor = GridBagConstraints.LINE_START;
		
		gbc.gridy = 0;
		gbc.gridx = 0;
		pnlPrincipal.add(lblNroCi, gbc);
		gbc.gridx = 1;
		pnlPrincipal.add(txtNroCi, gbc);
		gbc.gridy = 1;
		gbc.gridx = 0;
		pnlPrincipal.add(lblNombre, gbc);
		gbc.gridx = 1;
		pnlPrincipal.add(txtNombre, gbc);
		gbc.gridy = 2;
		gbc.gridx = 0;
		pnlPrincipal.add(lblApellido, gbc);
		gbc.gridx = 1;
		pnlPrincipal.add(txtApellido, gbc);
		gbc.gridy = 3;
		gbc.gridx = 0;
		pnlPrincipal.add(lblEmail, gbc);
		gbc.gridx = 1;
		pnlPrincipal.add(txtEmail, gbc);
		gbc.gridy = 4;
		gbc.gridx = 0;
		pnlPrincipal.add(lblFechaNac, gbc);
		gbc.gridx = 1;
		pnlPrincipal.add(txtFechaNac, gbc);
		gbc.gridy = 5;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		pnlPrincipal.add(btnAceptar, gbc);
		
		add(pnlPrincipal);

		btnAceptar.addActionListener(e->{
			String nroCi, nombre, apellido, email, fechaNac;
//			se obtienen todos los valores de los cuadros de texto
			nroCi = txtNroCi.getText();
			nombre = txtNombre.getText();
			apellido = txtApellido.getText();
			email = txtEmail.getText();
			fechaNac = txtFechaNac.getText();
//			se verifica que ningún campo sea vacío
			if(nroCi.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || fechaNac.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Campos vacíos!", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
//				se instancia un objeto de tipo Persona, para llamar al método de inserción
				Persona p = new Persona(Integer.parseInt(nroCi), nombre, apellido, email, fechaNac);
				if(Operaciones.insertarPersona(p)) {
					JOptionPane.showMessageDialog(this, "Registro exitoso!", "Registrar Persona", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(this, "Error al Registrar!", "Registrar Persona", JOptionPane.ERROR_MESSAGE);
				}
			}		
		});
	}
}
