package art.cipher581.commons.gui.util;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.SplashScreen;

import art.cipher581.commons.util.proc.IProcess;


public class SplashScreenUpdater extends Thread {

	private SplashScreen splashScreen;

	private IProcess process;

	private int barHeight;

	private int barWidth;

	private int barStartX = 0;

	private int barStartY;

	private Color colorDone = Color.GREEN;

	private Color colorOpen = Color.RED;


	public SplashScreenUpdater(SplashScreen splashScreen, IProcess process, int barHeight) {
		super("splsh-scrn-pdtr");

		this.splashScreen = splashScreen;
		this.process = process;

		int height = (int) splashScreen.getSize().getHeight();
		this.barHeight = barHeight;
		this.barWidth = (int) splashScreen.getSize().getWidth();
		this.barStartY = height - barHeight;
	}


	public void setBarProperties(int barHeight, int barWidth, int barStartX, int barStartY) {
		this.barHeight = barHeight;
		this.barWidth = barWidth;
		this.barStartX = barStartX;
		this.barStartY = barStartY;
	}


	@Override
	public void run() {
		while (!process.isFinished()) {
			double percent = process.getPercent();

			System.out.println("update: " + percent);
			update(percent);

			try {
				// System.out.println("sleep");
				Thread.sleep(100);
			} catch (Exception ex) {
				// ignore
			}
		}

		if (splashScreen.isVisible()) {
			splashScreen.close();
		}
	}


	private void update(double percent) {
		if (splashScreen.isVisible()) {
			Graphics2D g = splashScreen.createGraphics();

			renderProgressBar(g, barStartX, barStartY, barHeight, barWidth, percent);

			splashScreen.update();
		}
	}


	private void renderProgressBar(Graphics2D g, int posX, int posY, int height, int width, double percent) {
		int barWidth = (int) (((double) width) * percent);

		if (colorOpen != null) {
			g.setColor(colorOpen);
			g.fillRect(posX + barWidth, posY, width - barWidth, height);
		}

		g.setColor(colorDone);
		g.fillRect(posX, posY, barWidth, height);
	}


	public Color getColorDone() {
		return colorDone;
	}


	public void setColorDone(Color colorDone) {
		this.colorDone = colorDone;
	}


	public Color getColorOpen() {
		return colorOpen;
	}


	public void setColorOpen(Color colorOpen) {
		this.colorOpen = colorOpen;
	}

}
