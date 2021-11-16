# Stellar Networks

Stellar Networks is a pure-information oriented Starsector mod.
It adds three new intel tabs: Commodity, Market, and Storage.
Each tab comes with its own interactive board that can be used to manage presented intel.

## Available Boards

### Commodity

Commodity board (formerly [Galactic Markets](https://fractalsoftworks.com/forum/index.php?topic=19383)) is used to display buying and selling prices among all known markets.
It also hosts a Profit tab that can be used to find viable trade routes.

![Commodity](images/commodity.png?raw=1)

### Market

Market Queries board (formerly [Hyperspace Networks](https://fractalsoftworks.com/forum/index.php?topic=19252)) manages market searches. These can be either staff search (e.g. freelance administrator, steady officer, aggressive mercenary), item search (e.g. specific mod-spec or weapon), or ship search (e.g. pristine frigate, d-modded carrier).

![Add a New Tab](images/market1.png?raw=1)
![Query List](images/market2.png?raw=1)

Market Viewer board lets you select a market and preview content of all its submarkets.

![Viewer](images/viewer.png?raw=1)

### Storage

Storage board (formerly [Stellar Logistics](https://fractalsoftworks.com/forum/index.php?topic=18948)) displays all cargo and ships stored among all Storages.
It allows to display a unified view to quickly verify what is available, and per-location view to find out where.

![Storage](images/storage.png?raw=1)

## Installation and Configuration

To configure the mod edit [stelnet.dist.hjson](assets/stelnet.dist.hjson) and save it as `stelnet.hjson`.

This mod can be added to an existing playthrough - just enable it in the mod list and load the save.

This mod can be removed from an existing playthrough - edit `stelnet.hjson` and set `uninstallMod` to `true`.
Then load the save, save the game, quit the game, and finally disable this mod in the mod list.
