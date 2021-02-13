package art.cipher581.commons.gui.util;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListModel;


public class ComponentUtilities {

    public static Frame getFrame(Component component) {
        if (component == null) {
            return null;
        }

        Container container = (component instanceof Container) ? (Container) component : component.getParent();

        while (container != null) {
            Container parent = container.getParent();

            if (parent == container) {
                return null;
            } else if (container instanceof Frame) {
                return (Frame) container;
            }

            container = parent;
        }

        return null;
    }


    public static Dialog getDialog(Component component) {
        if (component == null) {
            return null;
        }

        Container container = component.getParent();

        while (container != null) {
            Container parent = container.getParent();

            if (parent == container) {
                return null;
            } else if (container instanceof Dialog) {
                return (Dialog) container;
            }

            container = parent;
        }

        return null;
    }


    public static void centerOnScreen(Window window) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        window.setLocation(screenSize.width / 2 - window.getWidth() / 2, screenSize.height / 2 - window.getHeight() / 2);
    }


    public static void centerOnComponent(Component child, Component parent) {
        if (parent == null) {
            System.out.println("parent is null");
        }

        if (child != null && parent != null) {
            int x = parent.getX() + (parent.getWidth() - child.getWidth()) / 2;
            int y = parent.getY() + (parent.getHeight() - child.getHeight()) / 2;

            System.out.println("location: (" + x + ", " + y + ")");

            // x = x < 0 ? 0 : x;
            y = y < 0 ? 0 : y;

            child.setLocation(x, y);
        }
    }


    public static <E> void fill(JComboBox<E> comboBox, Collection<E> entries) {
        @SuppressWarnings("unchecked")
		E[] arr = (E[]) entries.toArray();

        fill(comboBox, arr);
    }


    public static <E> void fill(JComboBox<E> comboBox, E[] objects) {
        fill(comboBox, objects, false);
    }


    public static <E> void fill(JComboBox<E> comboBox, E[] objects, boolean showEmpty) {
        DefaultComboBoxModel<E> comboBoxModel = new DefaultComboBoxModel<E>();

        if (showEmpty) {
            comboBoxModel.addElement(null);
        }

        for (E object : objects) {
            comboBoxModel.addElement(object);
        }

        comboBox.setModel(comboBoxModel);
    }


    public static void markAsInvalid(JTextField textComponent) {
        textComponent.setBackground(new Color(251, 196, 196));
    }


    public static void markAsValid(JTextField textComponent) {
        textComponent.setBackground(Color.WHITE);
    }


    public static <E> List<E> getContent(Container container, Class<E> clazz, boolean recursive) {
        List<E> matching = new LinkedList<E>();

        Component[] components = container.getComponents();

        if (components != null) {
            for (Component component : components) {
                if (component.getClass().equals(clazz)) {
                    @SuppressWarnings("unchecked")
                    E e = (E) component;

                    matching.add(e);
                }

                if (recursive) {
                    if (component instanceof Container) {
                        Container subContainer = (Container) component;

                        List<E> subMatching = getContent(subContainer, clazz, recursive);

                        matching.addAll(subMatching);
                    }
                }
            }
        }

        return matching;
    }


    public static <E extends Component> E getNearest(Class<E> clazz, Container container, Point point) {
        List<E> found = getContent(container, clazz, true);

        return getNearest(found, container, point);
    }


    public static <E extends Component> E getNearest(List<E> list, Container container, Point point) {
        double minDist = -1;
        E nearest = null;

        for (E component : list) {
            // get position of component within the container
            int x = component.getX();
            int y = component.getY();

            Container parent = component.getParent();

            while (parent != container) {
                x += parent.getX();
                y += parent.getY();

                parent = parent.getParent();
            }

            Point otherPoint = new Point(x, y);

            double d = getDistance(point, otherPoint);

            if (minDist == -1 || minDist > d) {
                minDist = d;
                nearest = component;
            }
        }

        return nearest;
    }


    public static double getDistance(Point a, Point b) {
        return Math.pow(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2), 0.5d);
    }


    public static void setToolTipInSubComponents(JComponent component, String toolTipText) {
        component.setToolTipText(toolTipText);

        Component[] components = component.getComponents();

        if (components != null) {
            for (Component subComponent : components) {
                if (subComponent instanceof JComponent) {
                    setToolTipInSubComponents((JComponent) subComponent, toolTipText);
                }

            }
        }
    }


    public static <E> void fill(JList<E> list, Collection<? extends E> items) {
        DefaultListModel<E> model = new DefaultListModel<E>();

        if (items != null) {
            for (E item : items) {
                model.addElement(item);
            }
        }

        list.setModel(model);
    }


    public static void removeFromList(JList<?> jList, int[] selectedIndices) {
        Arrays.sort(selectedIndices);

        ListModel<?> model = jList.getModel();

        if (model instanceof DefaultListModel<?>) {
            DefaultListModel<?> defaultListModel = (DefaultListModel<?>) model;

            for (int i = selectedIndices.length - 1; i >= 0; i--) {
                defaultListModel.remove(i);
            }
        }
    }


    public static <E> List<E> getContent(JList<E> jList) {
        DefaultListModel<E> model = (DefaultListModel<E>) jList.getModel();

        List<E> content = new LinkedList<E>();

        for (int i = 0; i < model.getSize(); i++) {
            E item = model.get(i);
            content.add(item);
        }

        return content;
    }


    public static void addMouseListeners(MouseListener[] mouseListeners, Component component) {
        for (MouseListener mouseListener : mouseListeners) {
            component.addMouseListener(mouseListener);
        }
    }


    @SuppressWarnings("unchecked")
	public static <E> E getParent(Component childComponent, Class<E> clazz) {
        if (childComponent == null) {
            return null;
        } else if (childComponent.getClass().equals(clazz)) {
            return (E) childComponent;
        } else {
            return getParent(childComponent.getParent(), clazz);
        }
    }


    public static Component getFrameOrDialog(Component source) {
        if (source == null) {
            return null;
        }

        Frame frame = getFrame(source);

        if (frame != null) {
            return frame;
        }

        Dialog dialog = getDialog(source);

        return dialog;
    }

}
