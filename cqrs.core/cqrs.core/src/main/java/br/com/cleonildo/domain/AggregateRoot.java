package br.com.cleonildo.domain;

import br.com.cleonildo.event.BaseEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter @Setter
public abstract class AggregateRoot {
    protected String id;
    private int version = -1;
    @Getter(AccessLevel.NONE)
    private final List<BaseEvent> changes = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(AggregateRoot.class.getName());

    /**
     * Returns a list of uncommitted changes.
     *
     * @return uncommitted changes
     */
    public List<BaseEvent> getUncimittedChanges() {
        return this.changes;
    }

    /**
     * Marks changes as committed.
     */
    public void markChangesAsCimmited() {
        this.changes.clear();
    }

    /**
     * Applies a change to the aggregate.
     *
     * @param event change to be applied
     * @param isNewEvent true if the event is a new event, false otherwise
     */
    public void applyChange(BaseEvent event, boolean isNewEvent) {
        try {
            Method method = getClass().getDeclaredMethod("apply", event.getClass());
            method.invoke(this,event);

        } catch (NoSuchMethodException e) {
            logger.log(
                    Level.WARNING,
                    MessageFormat.format("The applay method was not found in aggregate for {0}",
                            event.getClass().getName()
                    )
            );

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error applying event to aggregate", e);

        } finally {

            if (isNewEvent) {
                this.changes.add(event);
            }
        }
    }

    /**
     * Replays an event.
     *
     * @param event event to be replayed
     */
    public void reiseEvent(BaseEvent event) {
        this.applyChange(event, true);
    }

    /**
     * Replays a list of events.
     *
     * @param events list of events to be replayed
     */
    public void replayEvents(Iterable<BaseEvent> events) {
        events.forEach(ev -> this.applyChange(ev, false));
    }

}
