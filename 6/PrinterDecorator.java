public abstract class PrinterDecorator implements Printer {

    protected final Printer wrapped;

    protected PrinterDecorator(Printer wrapped) {
        if (wrapped == null) {
            throw new IllegalArgumentException("A decorator must wrap an existing printer.");
        }
        this.wrapped = wrapped;
    }

    @Override
    public void print(String message) {
        this.wrapped.print(decorate(message == null ? "" : message));
    }

    protected abstract String decorate(String message);
}
