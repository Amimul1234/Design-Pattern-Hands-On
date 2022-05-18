package ISP;

//No forcing should be applied

interface Printer {
    void print( Document d );
}

interface Scanner {
    void scan( Document d );

}

interface faxing {
    void fax( Document d );
}

interface Multifunction extends Printer, Scanner {

}

//YAGNI = You ain't going to need it
class MultifunctionPrinter implements Multifunction {

    @Override
    public void print( Document d ) {

    }

    @Override
    public void scan( Document d ) {

    }
}

class OldFashionedPrinter implements Printer {

    @Override
    public void print( Document d ) {

    }
}

public class Document {

}
