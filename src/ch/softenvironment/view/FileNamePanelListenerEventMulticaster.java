package ch.softenvironment.view;

/**
 * This is the event multicaster class to support the ch.softenvironment.view.FileNamePanelListenerEventMulticaster interface.
 */
public class FileNamePanelListenerEventMulticaster extends java.awt.AWTEventMulticaster implements ch.softenvironment.view.FileNamePanelListener {
/**
 * Constructor to support multicast events.
 * @param a java.util.EventListener
 * @param b java.util.EventListener
 */
protected FileNamePanelListenerEventMulticaster(java.util.EventListener a, java.util.EventListener b) {
	super(a, b);
}
/**
 * Add new listener to support multicast events.
 * @return ch.softenvironment.view.FileNamePanelListener
 * @param a ch.softenvironment.view.FileNamePanelListener
 * @param b ch.softenvironment.view.FileNamePanelListener
 */
public static ch.softenvironment.view.FileNamePanelListener add(ch.softenvironment.view.FileNamePanelListener a, ch.softenvironment.view.FileNamePanelListener b) {
	return (ch.softenvironment.view.FileNamePanelListener)addInternal(a, b);
}
/**
 * Add new listener to support multicast events.
 * @return java.util.EventListener
 * @param a java.util.EventListener
 * @param b java.util.EventListener
 */
protected static java.util.EventListener addInternal(java.util.EventListener a, java.util.EventListener b) {
	if (a == null)  return b;
	if (b == null)  return a;
	return new FileNamePanelListenerEventMulticaster(a, b);
}
/**
 * 
 * @return java.util.EventListener
 * @param oldl ch.softenvironment.view.FileNamePanelListener
 */
protected java.util.EventListener remove(ch.softenvironment.view.FileNamePanelListener oldl) {
	if (oldl == a)  return b;
	if (oldl == b)  return a;
	java.util.EventListener a2 = removeInternal(a, oldl);
	java.util.EventListener b2 = removeInternal(b, oldl);
	if (a2 == a && b2 == b)
		return this;
	return addInternal(a2, b2);
}
/**
 * Remove listener to support multicast events.
 * @return ch.softenvironment.view.FileNamePanelListener
 * @param l ch.softenvironment.view.FileNamePanelListener
 * @param oldl ch.softenvironment.view.FileNamePanelListener
 */
public static ch.softenvironment.view.FileNamePanelListener remove(ch.softenvironment.view.FileNamePanelListener l, ch.softenvironment.view.FileNamePanelListener oldl) {
	if (l == oldl || l == null)
		return null;
	if(l instanceof FileNamePanelListenerEventMulticaster)
		return (ch.softenvironment.view.FileNamePanelListener)((ch.softenvironment.view.FileNamePanelListenerEventMulticaster) l).remove(oldl);
	return l;
}
/**
 * 
 * @param newEvent java.util.EventObject
 */
public void textKeyReleased(java.util.EventObject newEvent) {
	((ch.softenvironment.view.FileNamePanelListener)a).textKeyReleased(newEvent);
	((ch.softenvironment.view.FileNamePanelListener)b).textKeyReleased(newEvent);
}
}
