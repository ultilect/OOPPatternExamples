package org.example.structuralPatterns;

/**
 * Паттерн для 'преобразования' 1 интерфейса в другой.
 * Может работать для классов(через множественное наследование) или
 * на уровне объектов(композиция).
 */
/*
    Например, заклинание в разных игровых вселенных
 */
public class AdapterExample {
    public static void test() {
        final Witcher witcher = new Witcher(new DndAttackSpellAdapter());
        System.out.printf("%s cast %s with damage %f",
                witcher.getClass().getName(),
                witcher.getAttackSpell().getClass().getName(),
                witcher.useAttackSpell());
    }
}

class Witcher {
    private final WitcherAttackSpell attackSpell;

    public Witcher(WitcherAttackSpell attackSpell) {
        this.attackSpell = attackSpell;
    }

    public Double useAttackSpell() {
        return this.attackSpell.create();
    }

    public WitcherAttackSpell getAttackSpell() {
        return attackSpell;
    }
}
interface DnDAttackSpell {
    Double cast();
}

interface WitcherAttackSpell {
    Double create();
}

class DnDFireball implements DnDAttackSpell {
    @Override
    public Double cast() {
        return 10.0;
    }
}

class DndAttackSpellAdapter implements WitcherAttackSpell {
    private final DnDAttackSpell spell;

    public DndAttackSpellAdapter() {
        this.spell = new DnDFireball();
    }

    @Override
    public Double create() {
        return this.spell.cast();
    }
}