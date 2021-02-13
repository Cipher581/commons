package art.cipher581.commons.util.proc;


import java.util.LinkedList;
import java.util.List;


public abstract class AbstractProcessThread<I extends IProcess> extends Thread implements IProcess {

    private final LinkedList<I> todo = new LinkedList<I>();

    private final LinkedList<I> done = new LinkedList<I>();

    private I current = null;

    private boolean finished = false;

    private boolean error = false;

    private Exception exception;


    public AbstractProcessThread(List<I> todo) {
        super();

        this.todo.addAll(todo);
    }


    @Override
    public boolean isFinished() {
        return finished;
    }


    @Override
    public double getPercent() {
        return calculatePercent();
    }


    public void run() {
        try {
            while (!todo.isEmpty()) {
                processNext();
            }
        } catch (Exception ex) {
            error = true;
            exception = ex;
        } finally {
            finished = true;
        }
    }


    public boolean isError() {
        return error;
    }


    public Exception getException() {
        return exception;
    }


    private void processNext() throws Exception {
        synchronized (this) {
            current = todo.pop();
        }

        process(current);

        synchronized (this) {
            done.add(current);
        }
    }


    protected abstract void process(I item) throws Exception;


    protected double calculatePercent() {
        double pct = ((double) getDone()) / ((double) getItems());

        if (pct > 1) {
            pct = 1;
        }

        return pct;
    }


    @Override
    public synchronized int getItems() {
        int items = 0;

        if (current != null) {
            items += current.getItems();
        }

        for (I item : this.done) {
            items += item.getItems();
        }

        for (I item : todo) {
            items += item.getItems();
        }

        System.out.println("items: " + items);

        return items;
    }


    @Override
    public synchronized int getDone() {
        int done = 0;

        if (current != null) {
            done += current.getDone();
        }

        for (I item : this.done) {
            done += item.getDone();
        }

        for (I item : todo) {
            done += item.getDone();
        }

        System.out.println("done: " + done);

        return done;
    }

}
