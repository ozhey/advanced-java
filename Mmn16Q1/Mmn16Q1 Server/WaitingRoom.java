// here clients can wait for another client to chat with them
// there's never more than one client waiting
public class WaitingRoom {
    
    private ChatThread firstClient;
    private ChatThread secondClient;

    // gets a ChatThread to chat with. If no one is waiting to chat, the thread will wait.
    // if there a ChatThread waiting, it will be returned and then notified.
    public synchronized ChatThread getChatBuddy(ChatThread client) {
        if (firstClient == null) {
            return handleFirstClient(client);
        } 
        return handleSecondClient(client);  
    }

    // the client is the first client in the waiting room, so it will wait.
    private synchronized ChatThread handleFirstClient(ChatThread client) {
        firstClient = client;
        while (secondClient == null) {
            try {
                wait();
            } catch (Exception e) { }
        } 
        ChatThread target = secondClient;
        // clear the waiting room for the next clients
        firstClient = null;
        secondClient = null;
        return target;
    }

    // there's already a client waiting for connection
    private synchronized ChatThread handleSecondClient(ChatThread client) {
        secondClient = client;
        notifyAll();
        return firstClient;    
    }

}
