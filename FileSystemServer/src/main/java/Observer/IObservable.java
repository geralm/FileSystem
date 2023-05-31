package Observer;

public interface IObservable {
    void addObserver(IObserver newObserver); //El que desea que ver el archivo compartido
    void removeObserver(IObserver observer); //El que ya no desea ver el archivo compartido
    void notifyAllObserver(Object var);
}
