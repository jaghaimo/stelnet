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
It also hosts a Profit tab that can be used to find viable trade routes.

![Commodity](images/commodity.png?raw=1)

### Market

Queries board (formerly [Hyperspace Networks](https://fractalsoftworks.com/forum/index.php?topic=19252)) manages market searches.
These can be either staff search (e.g. freelance administrator, steady officer, aggressive mercenary), item search (e.g. specific mod-spec or weapon), or ship search (e.g. pristine frigate, d-modded carrier).
![Add a New Tab](images/market1.png?raw=1)
![Query List](images/market2.png?raw=1)

Viewer board lets you select a market and preview content of its legal submarkets.

![Viewer](images/viewer.png?raw=1)

By default, both Market boards work with Open and Military Markets only.
To enable support for Black and Custom Markets edit `stelnet.json` (see below).

### Storage

Storage board (formerly [Stellar Logistics](https://fractalsoftworks.com/forum/index.php?topic=18948)) displays all cargo and ships stored among all Storages.
It allows to display a unified view to quickly verify what is available, and per-location view to find out where.

![Storage](images/storage.png?raw=1)

## Installation and Configuration

To configure the mod edit [stelnet.json](assets/stelnet.json).

This mod can be added to an existing save - just enable it in the mod list and load the save.

This mod can be removed from an existing save - edit `stelnet.json` and set `uninstallMod` to `true`.
Then load the save, save the game, quit the game, and finally disable this mod in the mod list.
