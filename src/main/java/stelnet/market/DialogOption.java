package stelnet.market;

public enum DialogOption {

    INIT("Go back"), EXIT("Finish"), STAFF("Search for staff"), CARGO("Search for cargo"), SHIP("Search for ships"),

    // staff type filter
    STAFF_TYPE_OFFICER("Staff: Officers") {
        @Override
        public DialogOption getNext() {
            return STAFF_TYPE_ADMIN;
        }
    },
    STAFF_TYPE_ADMIN("Staff: Administrators") {
        @Override
        public DialogOption getNext() {
            return STAFF_TYPE_OFFICER;
        }
    },

    // officers personality filter
    OFFICER_TIMID("Officer: Timid") {
        @Override
        public DialogOption getNext() {
            return OFFICER_CAUTIOUS;
        }
    },
    OFFICER_CAUTIOUS("Officer: Cautious") {
        @Override
        public DialogOption getNext() {
            return OFFICER_STEADY;
        }
    },
    OFFICER_STEADY("Officer: Steady") {
        @Override
        public DialogOption getNext() {
            return OFFICER_AGGRESSIVE;
        }
    },
    OFFICER_AGGRESSIVE("Officer: Aggresive") {
        @Override
        public DialogOption getNext() {
            return OFFICER_RECKLESS;
        }
    },
    OFFICER_RECKLESS("Officer: Reckless") {
        @Override
        public DialogOption getNext() {
            return OFFICER_TIMID;
        }
    },

    // cargo type filter
    CARGO_TYPE_WEAPON("Cargo: Weapons") {
        @Override
        public DialogOption getNext() {
            return CARGO_TYPE_FIGHTER;
        }
    },
    CARGO_TYPE_FIGHTER("Cargo: Fighter wings") {
        @Override
        public DialogOption getNext() {
            return CARGO_TYPE_MODSPEC;
        }
    },
    CARGO_TYPE_MODSPEC("Cargo: Modspecs") {
        @Override
        public DialogOption getNext() {
            return CARGO_TYPE_BLUEPRINT;
        }
    },
    CARGO_TYPE_BLUEPRINT("Cargo: Blueprints") {
        @Override
        public DialogOption getNext() {
            return CARGO_TYPE_WEAPON;
        }
    },

    // market weapon size filter
    WEAPON_SIZE_ANY("Size: Any") {
        @Override
        public DialogOption getNext() {
            return WEAPON_SIZE_SMALL;
        }
    },
    WEAPON_SIZE_SMALL("Size: Small") {
        @Override
        public DialogOption getNext() {
            return WEAPON_SIZE_MEDIUM;
        }
    },
    WEAPON_SIZE_MEDIUM("Size: Medium") {
        @Override
        public DialogOption getNext() {
            return WEAPON_SIZE_LARGE;
        }
    },
    WEAPON_SIZE_LARGE("Size: Large") {
        @Override
        public DialogOption getNext() {
            return WEAPON_SIZE_ANY;
        }
    },

    // market weapon type filter
    WEAPON_TYPE_ANY("Type: Any") {
        @Override
        public DialogOption getNext() {
            return WEAPON_TYPE_BALLISTIC;
        }
    },
    WEAPON_TYPE_BALLISTIC("Type: Ballistic") {
        @Override
        public DialogOption getNext() {
            return WEAPON_TYPE_ENERGY;
        }
    },
    WEAPON_TYPE_ENERGY("Type: Energy") {
        @Override
        public DialogOption getNext() {
            return WEAPON_TYPE_MISSILE;
        }
    },
    WEAPON_TYPE_MISSILE("Type: Missile") {
        @Override
        public DialogOption getNext() {
            return WEAPON_TYPE_ANY;
        }
    },

    // market wing type filter
    WING_TYPE_ANY("Type: Any") {
        @Override
        public DialogOption getNext() {
            return WING_TYPE_INTERCEPTOR;
        }
    },
    WING_TYPE_INTERCEPTOR("Type: Interceptors") {
        @Override
        public DialogOption getNext() {
            return WING_TYPE_FIGHTER;
        }
    },
    WING_TYPE_FIGHTER("Type: Fighters") {
        @Override
        public DialogOption getNext() {
            return WING_TYPE_BOMBER;
        }
    },
    WING_TYPE_BOMBER("Type: Bombers") {
        @Override
        public DialogOption getNext() {
            return WING_TYPE_ANY;
        }
    },

    // ship page
    SHIP_SIZE_FRIGATE("Size: Frigates") {
        @Override
        public DialogOption getNext() {
            return SHIP_SIZE_DESTROYER;
        }
    },
    SHIP_SIZE_DESTROYER("Size: Destroyers") {
        @Override
        public DialogOption getNext() {
            return SHIP_SIZE_CRUISER;
        }
    },
    SHIP_SIZE_CRUISER("Size: Cruisers") {
        @Override
        public DialogOption getNext() {
            return SHIP_SIZE_CAPITAL;
        }
    },
    SHIP_SIZE_CAPITAL("Size: Capitals") {
        @Override
        public DialogOption getNext() {
            return SHIP_SIZE_FRIGATE;
        }
    },

    // ship max d-mod count filter
    SHIP_DAMAGED_YES("Damaged: Include") {
        @Override
        public DialogOption getNext() {
            return SHIP_DAMAGED_NO;
        }
    },
    SHIP_DAMAGED_NO("Damaged: Exclude") {
        @Override
        public DialogOption getNext() {
            return SHIP_DAMAGED_ONLY;
        }
    },
    SHIP_DAMAGED_ONLY("Damaged: Only") {
        @Override
        public DialogOption getNext() {
            return SHIP_DAMAGED_YES;
        }
    },

    // ship carrier ship filter
    SHIP_CARRIER_YES("Carriers: Include") {
        @Override
        public DialogOption getNext() {
            return SHIP_CARRIER_NO;
        }
    },
    SHIP_CARRIER_NO("Carriers: Exclude") {
        @Override
        public DialogOption getNext() {
            return SHIP_CARRIER_ONLY;
        }
    },
    SHIP_CARRIER_ONLY("Carriers: Only") {
        @Override
        public DialogOption getNext() {
            return SHIP_CARRIER_YES;
        }
    },

    // ship civilian ship filter
    SHIP_CIVILIAN_YES("Civilian: Include") {
        @Override
        public DialogOption getNext() {
            return SHIP_CIVILIAN_NO;
        }
    },
    SHIP_CIVILIAN_NO("Civilian: Exclude") {
        @Override
        public DialogOption getNext() {
            return SHIP_CIVILIAN_ONLY;
        }
    },
    SHIP_CIVILIAN_ONLY("Civilian: Only") {
        @Override
        public DialogOption getNext() {
            return SHIP_CIVILIAN_YES;
        }
    };

    final private String name;

    private DialogOption(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public DialogOption getNext() {
        return null;
    }

    public boolean isType(String prefix) {
        return name().toString().startsWith(prefix);
    }
}
