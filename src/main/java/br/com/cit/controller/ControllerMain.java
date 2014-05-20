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
import javax.swing.JFrame;

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
	private static final String FATURAMENTO_MESSAGE_LISTENER = "faturamentoMessageListener";
	private static final String STOP = "Stop";
	private static final String START = "Start";


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
        final MenuItem startStop = new MenuItem(START);
        startStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(startStop.getLabel().equals(START)){
					startStop.setLabel(STOP);
					ApplicationContext context = new FileSystemXmlApplicationContext("D:/Ambiente-ControleEntregas/workspace/tcflAuto/src/main/resources/spring/applicationContext.xml");
					FaturamentoListener faturamentoListener = (FaturamentoListener) context.getBean(FATURAMENTO_MESSAGE_LISTENER);
					try {
						faturamentoListener.start();
					} catch (JMSException e1) {
						e1.printStackTrace();
					}
				}else{
					startStop.setLabel(START);
				}					
			}
		});
        
        MenuItem exitItem = new MenuItem("Sair");
       
        //Adicionando evento
        exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);
				System.out.println("Fechando.");
			}
		});

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
	
}
