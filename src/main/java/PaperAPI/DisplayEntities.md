# Display Entities

## Чем эта поеботина нам сдалась?
Замена для голограмм, летающих блоков/предметов и иных игровых сущностей, которые не являются `HumanEntity.class` (в старых версиях так было).   

## Как родилась дитятка?

> [!WARNING]
> Берёт своё начало с _1.19.4_.  

Оригинал статья на зарубежном сегменте:
- [mc wiki](https://minecraft.wiki/w/Display);
- [papermc.io](https://docs.papermc.io/paper/dev/display-entities/) (для разрабов, оттуда и берём примеры)

## Как это кодить?
### Типы
- TextDisplay
- BlockDisplay
- ItemDisplay

### Сие чудо код

Текст база.
```java
TextDisplay display = world.spawn(location, TextDisplay.class, entity -> {
    // customize the entity!
    entity.text(Component.text("Some awesome content", NamedTextColor.BLACK));
    entity.setBillboard(Display.Billboard.VERTICAL); // pivot only around the vertical axis
    entity.setBackgroundColor(Color.RED); // make the background red

    // see the Display and TextDisplay Javadoc, there are many more options
});
```

Блоки база.
```java
BlockDisplay display = world.spawn(location, BlockDisplay.class, entity -> {
    // customize the entity!
    entity.setBlock(Material.GRASS_BLOCK.createBlockData());
});
```

Итемы
```
ItemDisplay display = world.spawn(location, ItemDisplay.class, entity -> {
    // customize the entity!
    entity.setItemStack(ItemStack.of(Material.SKELETON_SKULL));
});
```

## Как это реализовывать чтоб все падали?
Кроме спавна мы бы хотели видеть как это всё чудо вертится, шизойдится и вообще красиво смотрится, да анимки бы забашлять, а то заебали дата-пак пастеры.   

Все эти проблемы решаются трансформацией.  
Всю инфу о трансформации читаем у [бумаги в доках](https://docs.papermc.io/paper/dev/display-entities/).  


Из основ надо запомнить:  
- scale (размер дитятки);  
- rotation (наклоны по осям X Y Z);  
- translation (смещение с осей, говорят по position offset).  
На этом приключения в кубическом мире математики окончены, перейдём к жиру.

Жир:
- interpolation (добавление плавности);
- teleportation (телепортация НЛО в любую точку жопы);
- transformation & Scheduler API (деградирование по вашим нуждам).

Что имеем? - Можно любой блок/предмет/айтем повернуть/уменьшить/смещать и при этом ещё делать это плавно телепортируя по карте, да и по вашим нуждам (временным) подойдёт.

Это имба которую надо было раньше сделать, наконец-таки они осознали как важен был **HolographicDisplays** и **DecentHolograms** юзерочкам.

## Поговорим о примерах использования.

### Плавно летающий меч вокруг своей оси по Y за 5 сек.

```java
ItemDisplay display = location.getWorld().spawn(location, ItemDisplay.class, entity -> {
    entity.setItemStack(ItemStack.of(Material.GOLDEN_SWORD));
});

int duration = 5 * 20; // duration of half a revolution (5 * 20 ticks = 5 seconds)

Matrix4f mat = new Matrix4f().scale(0.5F); // scale to 0.5x - smaller item
Bukkit.getScheduler().runTaskTimer(plugin, task -> {
    if (!display.isValid()) { // display was removed from the world, abort task
        task.cancel();
        return;
    }

    display.setTransformationMatrix(mat.rotateY(((float) Math.toRadians(180)) + 0.1F /* prevent the client from interpolating in reverse */));
    display.setInterpolationDelay(0); // no delay to the interpolation
    display.setInterpolationDuration(duration); // set the duration of the interpolated rotation
}, 1 /* delay the initial transformation by one tick from display creation */, duration);
```

### Упавший меч, который просто обитает в мире живом
```java
ItemDisplay display = location.getWorld().spawn(location, ItemDisplay.class, entity -> {
            entity.setItemStack(new ItemStack(Material.GOLDEN_SWORD));
            entity.setTransformationMatrix(
                    new Matrix4f()
                            .scale(0.5F) // Понизили размеры в 2 раза
                            .rotateXYZ(
                                    (float) Math.toRadians(360 - 90), // поворот -90 градусов по оси X
                                    0,
                                    0 //(float) Math.toRadians(90) // поворот +90 градусов по оси Z
                            )
            );
        });
```

#### Советы.
Послушать зарубежного умняшку можно на [ютабчике к слову](https://youtu.be/8fKEG2Pj1vQ).  

Трансформировать `display` можно как через `setTransformation`, так и через `setTransformationMatrix`, но при этом он всё равно создавать будет классы, поэтому вы не избежите рам ликов.