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
public class FormEditar extends JFrame{
	private JPanel pnlPrincipal;
	private JLabel lblNroCi, lblNombre, lblApellido, lblEmail, lblFechaNac;
	private JTextField txtNroCi, txtNombre, txtApellido, txtEmail, txtFechaNac;
	private JButton btnEditar, btnEliminar;
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	public FormEditar() {
		super("Editar Persona");
		setSize(400,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		pnlPrincipal = new JPanel(new GridBagLayout());
		lblNroCi = new JLabel("Nro CI:");
		lblNombre = new JLabel("Nombre:");
		lblApellido = new JLabel("Apellido:");
		lblEmail = new JLabel("Email:");
		lblFechaNac = new JLabel("FechNac:");
		
		txtNroCi = new JTextField(12);
//		evitar la edición del nroCi
		txtNroCi.setEditable(false);
		txtNombre = new JTextField(12);
		txtApellido = new JTextField(12);
		txtEmail = new JTextField(12);
		txtFechaNac = new JTextField(12);
		
		btnEditar = new JButton("Editar");
		btnEliminar = new JButton("Eliminar");

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
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 5;
		gbc.gridx = 0;
		pnlPrincipal.add(btnEditar, gbc);
		gbc.gridx = 1;
		pnlPrincipal.add(btnEliminar, gbc);
		
		add(pnlPrincipal);

		btnEditar.addActionListener(e->{
			String nroCi, nombre, apellido, email, fechaNac;
			
			nroCi = txtNroCi.getText();
			nombre = txtNombre.getText();
			apellido = txtApellido.getText();
			email = txtEmail.getText();
			fechaNac = txtFechaNac.getText();

			if(nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || fechaNac.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Campos vacíos!", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				Persona p = new Persona(Integer.parseInt(nroCi), nombre, apellido, email, fechaNac);
				if(Operaciones.editarPersona(p)) {
					JOptionPane.showMessageDialog(this, "Edición exitosa!", "Editar Persona", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(this, "Error al Editar!", "Editar Persona", JOptionPane.ERROR_MESSAGE);
				}
			}		
		});
		
		btnEliminar.addActionListener(e->{
//			permite confirmar la operación
			int confirmar = JOptionPane.showConfirmDialog(this, "Confirmar eliminación permanente de los datos!", 
					"Eliminar datos de Persona", JOptionPane.OK_CANCEL_OPTION);
			if(confirmar == JOptionPane.OK_OPTION) {
				if(Operaciones.eliminarPersona(Integer.parseInt(txtNroCi.getText()))) {
					JOptionPane.showMessageDialog(this, "Datos Eliminados correctamente!", "Editar Persona", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(this, "Error al Eliminar los datos!", "Editar Persona", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	/**
	 * Método que permite cargar los cuadros de texto con los datos de la Persona, proveída por parámetro desde el
	 * FormPrincipal
	 * @param p Persona
	 */
	public void cargarCampos(Persona p) {
		txtNroCi.setText(p.getNroCi().toString());
		txtNombre.setText(p.getNombre());
		txtApellido.setText(p.getApellido());
		txtEmail.setText(p.getEmail());
		txtFechaNac.setText(p.getFechaNac());
	}
}
