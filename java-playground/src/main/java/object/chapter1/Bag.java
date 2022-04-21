package object.chapter1;

public class Bag {
    private int amount;
    private final Invitation invitation;
    private Ticket ticket;

    public Bag(int amount, Invitation invitation) {
        this.amount = amount;
        this.invitation = invitation;
    }

    public boolean hasInvitation() {
        return invitation != null;
    }

    public int hold(Ticket ticket) {
        if (hasInvitation()) {
            setTicket(ticket);
            return 0;
        } else {
            minusAmount(ticket.getFee());
            setTicket(ticket);
            return ticket.getFee();
        }
    }

    private void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    private void minusAmount(int amount) {
        int remainedAmount = this.amount - amount;

        if (remainedAmount < 0) {
            throw new RuntimeException("가방 - 잔액이 부족함");
        }
        this.amount = remainedAmount;
    }

    private void plusAmount(int amount) {
        this.amount += amount;
    }
}