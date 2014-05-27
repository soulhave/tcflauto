package br.com.cit.controller;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.jms.JMSException;
import javax.swing.ImageIcon;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import br.com.cit.jms.listener.FaturamentoListener;

/**
 * Open try
 * 
 * @author ramon
 * 
 */
public class ControllerMain {
	
	private static final String ON_START_STOP = "startStop";
	private static final String ON_EXIT = "exit";
	private static final String FATURAMENTO_MESSAGE_LISTENER = "faturamentoMessageListener";
	private static final String STOP = "Stop";
	private static final String START = "Start";
	
	private static MenuItem startStop;
	private static MenuItem exitItem;
	private static ApplicationContext context;

	/**
	 * Metodo principal
	 * @param args
	 */
	public static void main(String[] args) {
        
		if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon = new TrayIcon(createImage("src/main/resources/img/vale_icon.png", "tray icon"));
        final SystemTray tray = SystemTray.getSystemTray();
       
        // Create a pop-up menu components
        startStop = new MenuItem(START);
        startStop.addActionListener(Acao.getInstance());
        startStop.setActionCommand(ON_START_STOP);
        
        exitItem = new MenuItem("Sair");
        exitItem.setActionCommand(ON_EXIT);
        exitItem.addActionListener(Acao.getInstance());

        //Add components to pop-up menu
        popup.add(startStop);
        popup.addSeparator();
        popup.add(exitItem);
       
        trayIcon.setPopupMenu(popup);
        
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
	}

	
	/**
	 * Cria imagem
	 * @param Path da imagem
	 * @param String de descri��o da imagem.
	 * @return
	 */
	public static Image createImage(String path, String strId) {
		return new ImageIcon(path, strId).getImage();
	}


	/**
	 * Inicia a escuta do Listener e para 
	 * @param startStop
	 */
	private static void onStopStartListener(final MenuItem startStop) {
		context = new FileSystemXmlApplicationContext("D:/Ambiente-ControleEntregas/workspace/tcflAuto/src/main/resources/spring/applicationContext.xml");
		FaturamentoListener faturamentoListener = (FaturamentoListener) context.getBean(FATURAMENTO_MESSAGE_LISTENER);
		try {
			if(startStop.getLabel().equals(START)){
				startStop.setLabel(STOP);
				faturamentoListener.start();
			}else{
				startStop.setLabel(START);
				faturamentoListener.stop();;
			}					
		} catch (JMSException e1) {
			e1.printStackTrace();
		}finally{
		}
	}
	
	/**
	 * Classe responsável pelos eventos.
	 * @author ramon
	 *
	 */
	private static final class Acao implements ActionListener {

		private static Acao S_INSTANCE;
		
		/**
		 * Static singletone
		 * @return
		 */
		public static Acao getInstance() {
			if(S_INSTANCE==null)
				S_INSTANCE = new Acao();
			return S_INSTANCE;
		}
		
		private Acao() {}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//Start stop
			if(ON_START_STOP.equals(e.getActionCommand())){
				onStopStartListener(startStop);
			}
			//Start stop
			if(ON_EXIT.equals(e.getActionCommand())){
				System.exit(0);
			}
		}
	}

	public static ApplicationContext getContext() {
		return context;
	}


	public static void setContext(ApplicationContext context) {
		ControllerMain.context = context;
	}
	
}
