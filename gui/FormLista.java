package tsi.rp.s1.gui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import tsi.rp.s1.modelo.Operaciones;
import tsi.rp.s1.modelo.Persona;

@SuppressWarnings("serial")
public class FormLista extends JFrame{
	private JPanel pnlPrincipal;
	private JTextArea txtLista;
	private JButton btnAceptar,btnGuardar;
	
	public FormLista() {
		super("Listado de Personas");
		setSize(400,320);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		pnlPrincipal = new JPanel();
		txtLista = new JTextArea(15,30);
		txtLista.setEditable(false);
		btnAceptar = new JButton("Aceptar");
		btnGuardar = new JButton("Guardar");
		
		btnAceptar.addActionListener(e->dispose());
		btnGuardar.addActionListener(e->{
			try (
					
					FileWriter fw = new FileWriter("Datos de usuarios.txt");
				){
//				se crea el contenido que será agregado al archivo
				String dato = cargarDatitos();
//				se escribe el "dato" en el archivo
				
				fw.write(dato);
			} catch (IOException e1) {
				System.out.println("Error al crear y escribir un archivo de texto, " + e1.getMessage());
			}
		});

		add(pnlPrincipal);
		pnlPrincipal.add(new JScrollPane(txtLista));
		pnlPrincipal.add(btnAceptar);
		pnlPrincipal.add(btnGuardar);
			
		setLocationRelativeTo(null);
	}
	
	public void cargarDatos() {
		ArrayList<Persona> lp = Operaciones.listarPersonas();
		String datos = "";
		for(Persona p : lp) {
			datos += "Id: " + p.getIdP() + "\nCi: " + p.getNroCi() 
			+ "\nNombre y Apellido: " + p.getNombre() + ", " 
			+ p.getApellido() 
			+ "\nEmail: " + p.getEmail() 
			+ "\nFecha de Nacimiento: " + p.getFechaNac() + "\n";
		}
		txtLista.setText(datos);
		txtLista.setCaretPosition(0);
	}
	public void cargarDatos(Persona p) {
		String datos = "Id: " + p.getIdP() + "\nCi: " + p.getNroCi() 
		+ "\nNombre y Apellido: " + p.getNombre() + ", " 
		+ p.getApellido() 
		+ "\nEmail: " + p.getEmail() 
		+ "\nFecha de Nacimiento: " + p.getFechaNac() + "\n";
		txtLista.setText(datos);
		txtLista.setCaretPosition(0);
	}
	public String cargarDatitos() {
		ArrayList<Persona> lp = Operaciones.listarPersonas();
		String datos = "";
		for(Persona p : lp) {
			datos += "Id: " + p.getIdP() + "\nCi: " + p.getNroCi() 
			+ "\nNombre y Apellido: " + p.getNombre() + ", " 
			+ p.getApellido() 
			+ "\nEmail: " + p.getEmail() 
			+ "\nFecha de Nacimiento: " + p.getFechaNac() + "\n";
		}
		txtLista.setText(datos);
		txtLista.setCaretPosition(0);
		return datos;
	}
}
