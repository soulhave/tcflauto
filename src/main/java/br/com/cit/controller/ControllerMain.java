package br.com.cit.controller;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Open try
 * 
 * @author ramon
 * 
 */
public class ControllerMain {
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
        final TrayIcon trayIcon = new TrayIcon(createImage("src/main/resources/vale_icon.png", "tray icon"));
        final SystemTray tray = SystemTray.getSystemTray();
       
        // Create a pop-up menu components
        MenuItem aboutItem = new MenuItem("Sobre");
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
        popup.add(aboutItem);
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
	 * @param String de descrição da imagem.
	 * @return
	 */
	public static Image createImage(String path, String strId) {
		return new ImageIcon(path, strId).getImage();
	}
		
}
