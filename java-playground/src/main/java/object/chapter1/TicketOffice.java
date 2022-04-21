package object.chapter1;

import java.util.ArrayList;
import java.util.List;

public class TicketOffice {

    private List<Ticket> tickets = new ArrayList<>();
    private int amount;

    public TicketOffice(List<Ticket> tickets, int amount) {
        this.tickets = tickets;
        this.amount = amount;
    }

    public Ticket getTicket() {
        if (tickets.isEmpty()) {
            throw new RuntimeException("매진됨");
        }

        final Ticket ticket = tickets.get(0);
        tickets.remove(ticket);
        return ticket;
    }

    public void minusAmount(int amount) {
        int remainedAmount = this.amount - amount;

        if (remainedAmount < 0) {
            throw new RuntimeException("티켓 오피스 - 잔액이 부족함");
        }
        this.amount = remainedAmount;
    }

    public void plusAmount(int amount) {
        this.amount += amount;
    }

    public void sellTicketTo(Audience audience) {
        plusAmount(audience.buy(getTicket()));
    }
}