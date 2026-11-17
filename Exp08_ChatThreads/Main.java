/**
 * Experiment 8: Multithreaded chat simulation.
 * Each user is a thread. Demonstrates priorities, isAlive(), join(),
 * and safe pause / resume / stop using wait() and notifyAll()
 * (Thread.suspend/resume/stop are deprecated and unsafe).
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ChatUser admin = new ChatUser("Admin", Thread.MAX_PRIORITY,
                new String[]{"System maintenance at 6 PM", "Please save your work"});
        ChatUser bob = new ChatUser("Bob", Thread.NORM_PRIORITY,
                new String[]{"Hi everyone", "How is the project going?"});
        ChatUser carol = new ChatUser("Carol", Thread.MIN_PRIORITY,
                new String[]{"Good morning", "Lunch at 1?"});

        ChatUser[] users = {admin, bob, carol};

        System.out.println("=== Starting chat (Admin = high priority) ===");
        for (ChatUser u : users) u.start();

        Thread.sleep(1200);
        System.out.println("\n>>> isAlive check:");
        for (ChatUser u : users)
            System.out.println("    " + u.getName() + " alive? " + u.isAlive());

        System.out.println("\n>>> Pausing Bob...");
        bob.pauseThread();
        Thread.sleep(1500);

        System.out.println("\n>>> Resuming Bob...");
        bob.resumeThread();
        Thread.sleep(1200);

        System.out.println("\n>>> Terminating all users...");
        for (ChatUser u : users) u.stopThread();

        for (ChatUser u : users) u.join();   // wait for every thread to finish

        System.out.println("\n>>> Final status after join():");
        for (ChatUser u : users)
            System.out.println("    " + u.getName() + " alive? " + u.isAlive());
        System.out.println("Chat session ended.");
    }
}

class ChatUser extends Thread {
    private final String[] messages;
    private volatile boolean running = true;
    private boolean paused = false;

    ChatUser(String name, int priority, String[] messages) {
        super(name);
        setPriority(priority);
        this.messages = messages;
    }

    @Override
    public void run() {
        int i = 0;
        try {
            while (running) {
                synchronized (this) {
                    while (paused && running) wait();
                }
                if (!running) break;

                String msg = messages[i++ % messages.length];
                System.out.println("[" + getName() + " | priority " + getPriority() + "] sending: " + msg);
                Thread.sleep(400);
                System.out.println("[" + getName() + "] received reply to: " + msg);
                Thread.sleep(400);
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupted.");
        }
        System.out.println(getName() + " has left the chat.");
    }

    synchronized void pauseThread() {
        paused = true;
    }

    synchronized void resumeThread() {
        paused = false;
        notifyAll();
    }

    synchronized void stopThread() {
        running = false;
        paused = false;
        notifyAll();
    }
}
