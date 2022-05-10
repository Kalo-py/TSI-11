package tsi.rp.s1.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tsi.rp.s1.modelo.Operaciones;
import tsi.rp.s1.modelo.Persona;

@SuppressWarnings("serial")
public class FormPrincipal extends JFrame{
	private JPanel pnlPrincipal;
	private JMenuBar barraMenu;
	private JMenu mAccion, mReportes;
	private JMenuItem miListar, miNuevo, miEditar, miSalir, miBuscar;
	
	public FormPrincipal() {
		super("Registro de Personas");
		setSize(600,400);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cerrarApp();
			}
		});
		
		pnlPrincipal = new JPanel();
		barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);
		mAccion = new JMenu("Acciones");
		mReportes = new JMenu("Reportes");
		miListar = new JMenuItem("Listar");
		miNuevo = new JMenuItem("Registrar");
		miEditar = new JMenuItem("Editar por Nro CI");
		miSalir = new JMenuItem("Salir");
		miBuscar = new JMenuItem("Buscar por Nro CI");
		
		add(pnlPrincipal);
		barraMenu.add(mAccion);
		mAccion.add(miListar);
		mAccion.add(miNuevo);
		mAccion.add(miEditar);
		barraMenu.add(mReportes);
		mReportes.add(miBuscar);
		mAccion.addSeparator();
		mAccion.add(miSalir);
		
		FormLista frmL = new FormLista();
		miListar.addActionListener(e->{
			frmL.cargarDatos();
			frmL.setVisible(true);
		});
		
//		se instancia un objeto de tipo Formulario Nuevo para mejor control del mismo
		FormNuevo frmN = new FormNuevo();
		miNuevo.addActionListener(e->{
//			esto permite mostrar el formulario dentro del formPrincipal
			frmN.setLocationRelativeTo(this);
			frmN.setVisible(true);
		});
		
		FormEditar frmE = new FormEditar();
		miEditar.addActionListener(e->{
			Persona p = null;
//			solicitar el nroCi
			String ci = JOptionPane.showInputDialog(this, "Nro CI: ", 
					"Buscar datos de Persona a Editar", JOptionPane.QUESTION_MESSAGE);
			if(ci == null) {
//				botón cancelar presionado
			}else {
				if(ci.isEmpty() && ci != null) {
//					cuadro de texto en blanco y presionó aceptar
					JOptionPane.showMessageDialog(this, "Debe proveer un nroCi!", "Editar Persona", JOptionPane.ERROR_MESSAGE);
				}else {
//					buscar si la persona existe en la BD
					p = Operaciones.buscarPersonaPorCi(Integer.parseInt(ci));
					if(p!=null) {
//						si existe mostrar el formulario pasando como parámetro el objeto Persona
						frmE.cargarCampos(p);
						frmE.setLocationRelativeTo(this);
						frmE.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(this, "Datos no encontrados!", "Editar Persona", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		miBuscar.addActionListener(e->{
			Persona p = null;
			String ci = JOptionPane.showInputDialog(this, "Nro CI: ", 
					"Buscar datos de la Persona por su CI", JOptionPane.QUESTION_MESSAGE);
			if(ci == null) {
//				botón cancelar presionado
			}else {
				if(ci.isEmpty() && ci != null) {
//					cuadro de texto en blanco y presionó aceptar
					JOptionPane.showMessageDialog(this, "Debe proveer un nroCi!", "Buscar Persona", JOptionPane.ERROR_MESSAGE);
				}else {
					p = Operaciones.buscarPersonaPorCi(Integer.parseInt(ci));
					if(p!=null) {
//						si existe mostrar el formulario pasando como parámetro el objeto Persona
						frmL.cargarDatos(p);
						frmL.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(this, "Datos no encontrados!", "Buscar Persona", JOptionPane.WARNING_MESSAGE);
					}
				}
				}
		});
		
		miSalir.addActionListener(e->cerrarApp());
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void cerrarApp() {
		if(JOptionPane.showConfirmDialog(this, "Desea cerrar la aplicación?",
				"Cerrar Aplicación", JOptionPane.OK_CANCEL_OPTION) == 
				JOptionPane.OK_OPTION) {
			dispose();
		}
	}
}
