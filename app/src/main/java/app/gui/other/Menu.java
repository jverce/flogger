package app.gui.other;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;

import app.gui.Gui;
import app.gui.dinamicobjects.IPaintable;

public class Menu implements IPaintable {
	private Selector selector;
	private Vector<ISelectionable> items;
	
	public Menu(Gui g) {
		this.selector = new Selector(this, g);
		this.items = new Vector<ISelectionable>();
	}
	
	public int size() {
		return this.items.size();
	}
	
	public void addItem(ISelectionable item) {
		this.items.add(item);
	}
	
	public void paint(Graphics g, int itemNumber) {
		this.selector.paint(g, itemNumber);
		for (int i = 0; i < size(); i++) {
			this.items.get(i).paint(g, i + itemNumber);
		}
	}
	
	public void paint(Graphics g) {
		this.paint(g, 0);
	}
	
	public void incSelector() {
		this.selector.inc();
	}
	
	public void decSelector() {
		this.selector.dec();
	}
	
	public void selectItem() {
		this.items.get(selector.value()).select();
	}
	
	public Rectangle getRectangle() {
		return null;
	}
}