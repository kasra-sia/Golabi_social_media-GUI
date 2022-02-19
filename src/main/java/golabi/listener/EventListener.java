package golabi.listener;


import golabi.event.RegistrationFormEvent;

public interface EventListener {
    <T>void eventOccurred(T event) throws Exception;
}