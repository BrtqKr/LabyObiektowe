package agh.cs.Court.orders;

public class orderI
{
    private String helpString="Dostępne komendy: "+System.lineSeparator()+"Quit - zakończenie pracy programu"+
            System.lineSeparator()+"rubrum - wyświetlenie metryki jednego lub wielu orzeczeń, na podstawie sygnatury"+System.lineSeparator()+
            "content - wyświetlenie uzasadnienia"+System.lineSeparator()+"judge - wyświetlenie liczby orzeczeń dla wybranego sędziego"+
            System.lineSeparator()+"judges - wyświetlenie 10 sędziów o największej liczbie wydanych orzeczeń"+System.lineSeparator()+
            "months - wyświetlenie liczby orzeczeń w poszczególnych miesiącach"+System.lineSeparator()+"courts - wyświetlenie liczby orzeczeń ze względu na typ sądu"+
            System.lineSeparator()+"regulations - wyświetlenie 10 najczęściej przywoływanych ustaw"+System.lineSeparator()+"jury - wyświetlenie liczby spraw przypadających na określony skład sędziowski";

    public String getHelpString() {
        return helpString;
    }
}
