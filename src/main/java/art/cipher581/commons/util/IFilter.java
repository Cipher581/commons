package art.cipher581.commons.util;


public interface IFilter<E> {

    public void evaluate(E obj);


    public boolean accept(E obj);

}
