package ru.job4j.cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {

    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        boolean result = false;
        if (getById(account.getId()).isEmpty()) {
            accounts.put(account.getId(), account);
        }
        return result;
    }

    public synchronized boolean update(Account account) {
        boolean result = false;
        if (getById(account.getId()).isPresent()) {
            accounts.replace(accounts.get(account.getId()).getId(), account);
            result = true;
        }
        return result;
    }

    public synchronized boolean delete(int id) {
        boolean result = false;
        if (getById(id).isPresent()) {
            accounts.remove(id);
            result = true;
        }
        return result;
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        if (getById(fromId).isEmpty() && getById(toId).isEmpty()) {
            throw new IllegalArgumentException("One of the accounts are not valid");
        }
        Account fromAccount = accounts.get(fromId);
        Account toAccount = accounts.get(toId);
        if (fromAccount.amount() < amount) {
            throw new IllegalArgumentException("Insufficient funds to transfer");
        }
        fromAccount.setAmount(fromAccount.amount() - amount);
        toAccount.setAmount(toAccount.amount() + amount);
        return true;
    }

}
