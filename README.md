# Stellar Networks

Stellar Networks is a QoL oriented Starsector mod.
It adds three new intel tabs: Commodity, Market, and Storage.
Each tab comes with interactive boards that are used to manage presented intel.

## Available Boards

### Contacts

Contacts board presents a filterable (by priority, by type) list of all your active contacts.
Using the board you can quickly navigate to see each contact's intel details, or directly call the person.
All items and ships acquired during the call will be deposited to the Storage of the respective market.

![Contacts](images/contacts.png?raw=1)

### Commodity

Commodity board (formerly [Galactic Markets](https://fractalsoftworks.com/forum/index.php?topic=19383)) is used to display buying and selling prices among all known markets.

![Commodity](images/commodity.png?raw=1)

#### Profit

Commodity board also hosts a Profit tab which can be used to find viable trade routes.
Filters by most profitable routes in descending order and displays routes where:

-   profit margin is at least 10000 credits, and
-   trade volume is at least 1000.

Highlights rows when buy and sell markets are in the same system and caps results to first 50.

![Profit](images/profit.png?raw=1)

### Market

Queries board (formerly [Hyperspace Networks](https://fractalsoftworks.com/forum/index.php?topic=19252)) manages market searches.
These can be either staff search (e.g. freelance administrator, steady officer, aggressive mercenary), item search (e.g. specific mod-spec or weapon), or ship search (e.g. pristine frigate, d-modded carrier).
![Add a New Tab](images/market1.png?raw=1)
![Query List](images/market2.png?raw=1)

Viewer board lets you select a market and preview content of its legal submarkets.

![Viewer](images/viewer.png?raw=1)

By default, both Market boards work with Open Military, and Black Markets only.
To enable support for Custom Markets edit `stelnet.json` (see below).

### Storage

Storage board (formerly [Stellar Logistics](https://fractalsoftworks.com/forum/index.php?topic=18948)) displays all cargo and ships stored among all Storages.
It allows to display a unified view to quickly verify what is available, and per-location view to find out where it is.

![Storage](images/storage.png?raw=1)

## Installation and Configuration

To configure the open LunaLib menu (F2 by default).

This mod can be added to an existing save - just enable it in the mod list and load the save.

This mod can be removed from an existing save - in LunaLib menu set `Uninstall Mod` to `true`.
Then load the save, save the game, quit the game, and finally disable this mod in the mod list.

## Translating the Mod

To translate this mod, edit files in [data/l10n](assets/data/l10n/) directory.
You can then create the translation mod with just the following structure:

```bash
data/config/tag_data.json  # tag names in Intel UI
data/l10n/                 # strings used in the mod
mod_info.json              # the usual content, no need for mod plugin
```

## Integration with Other Mods

See various CSV files in [data/stelnet](assets/data/stelnet/) directory.
