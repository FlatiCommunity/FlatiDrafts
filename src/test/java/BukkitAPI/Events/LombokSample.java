import lombok.*;
import org.bukkit.event.*;
import org.bukkit.entity.*;

// Можно заменить на @Data, но он добавит @ToString и @EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
/**
 * Source lombok: https://projectlombok.org/features/
 */
public class LombokSampleEvent extends Event {

    private final Player player; // допустим нам нужен игрок
    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
