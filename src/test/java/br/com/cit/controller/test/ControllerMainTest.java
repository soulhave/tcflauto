package br.com.cit.controller.test;

import javax.swing.ImageIcon;

import org.junit.Assert;
import org.junit.Test;

import br.com.cit.controller.ControllerMain;

/**
 * Open try
 * 
 * @author ramon
 * 
 */
public class ControllerMainTest {
	@Test
	public void createImageTest() {
		String path = "/src/main/resources/img/vale_icon.png";
		String descricao = "try icon";
		Assert.assertEquals(ControllerMain.createImage(path, descricao), new ImageIcon(path,descricao).getImage());
	}
	
	@Test
	public void mainTest() {
		ControllerMain.main(null);
	}
}
