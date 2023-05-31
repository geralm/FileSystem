package Observer;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class AbstractObservable implements IObservable{
    //Esto sería el archivo que están compartiendo
    private final ArrayList<IObserver> observers = new ArrayList<>();
    public AbstractObservable(){};

    public void addObserver(IObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        this.observers.remove(observer);
    }

    public void notifyAllObservers(Object source) {
        Iterator var2 = this.observers.iterator();
        while(var2.hasNext()) {
            IObserver observer = (IObserver)var2.next();
            observer.notifyObserver(source);
        }

    }
}
