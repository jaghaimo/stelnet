# Stellar Networks

Stellar Networks is a QoL oriented Starsector mod.
It adds three new intel tabs: Commodity, Market, and Storage.
Each tab comes with interactive boards that are used to manage presented intel.
Finally, the mod extends Contacts and Exploration tab by adding QoL boards to it.

For more details, see [this forums thread](https://fractalsoftworks.com/forum/index.php?topic=20836).

## Contributing to the Mod

Non technical contribution (ideas or bug reports) can be made via [Issues](https://github.com/jaghaimo/stelnet/issues).

In order to contribute code please fork this repository and work on your idea there.
Once you have enough to share, open a Pull Request and I will review your changes.
We will then work together to incorporate your changes into a next release.

## Translating the Mod

To translate this mod, edit files in [data/l10n](assets/data/l10n/) directory.
You can then create the translation mod with just the following structure:

```bash
data/config/LunaSettings.csv  # LunaLib settings: headers and description
data/config/tag_data.json     # tag names in Intel UI
data/l10n/                    # strings used in the mod
mod_info.json                 # the usual content, no need for mod plugin
```

## Integration with Other Mods

See various CSV files in [data/stelnet](assets/data/stelnet/) directory.
