# Stellar Networks

[![latest release](https://img.shields.io/github/v/release/jaghaimo/stelnet?label=download%20latest)](https://github.com/jaghaimo/stelnet/releases/latest)
[![Github All Releases](https://img.shields.io/github/downloads/jaghaimo/stelnet/total.svg)](https://github.com/jaghaimo/stelnet/releases)

Stellar Networks is a QoL oriented Starsector mod.
It adds three new intel tabs: Commodity, Market, and Storage.
Each tab comes with interactive boards that are used to manage presented intel.
Finally, the mod extends Contacts and Exploration tab by adding QoL boards to it.

For more details, see [this forums thread](https://fractalsoftworks.com/forum/index.php?topic=20836).

## Contributing to the Mod

Non-technical contribution (ideas or bug reports) can be made via [Issues](https://github.com/jaghaimo/stelnet/issues).

In order to contribute code please fork this repository and work on your idea there.
Once you have enough to share, open a Pull Request and I will review your changes.
We will then work together to incorporate your changes into a next release.

### Translating the Mod

To translate this mod, edit files in [data/l10n](assets/data/l10n) directory.
You can then create the translation mod with just the following structure:

```yaml
data/config/LunaSettings.csv  # LunaLib settings: headers and description
data/config/tag_data.json     # tag names in Intel UI
data/l10n/                    # strings used in the mod
mod_info.json                 # the usual content, no need for mod plugin
```

### Integration with Other Mods

To be able to exclude/include markets and storages in the Stelnet intel tab results see various CSV files in the
[data/stelnet](assets/data/stelnet) directory. Those files can be either edited in the Stelnet mod directory,
or created in another mod's data directory. For example, to exclude a faction in your mod create a file:  
`starsector/mods/{your_mod_dir}/data/stelnet/exclude/market_by_faction.csv`
<details><summary>content</summary>

```csv
id
hegemony
```
</details>

Mods introducing new commodities may hinder finding the basic commodities like supplies or fuel.
Commodity Market intel board can be forces to show commodities from a CSV file on the top of the right pane list,
followed by the remaining commodities sorted in alphabetical order. Example `data/stelnet/sort/priority_commodities.csv`:
<details><summary>content</summary>

```csv
id
supplies
fuel
crew
marines
```
</details>


### Testing unreleased versions

* Clone this repository.
* Checkout branch you want to try out.
* <details><summary>Ensure your `JAVA_HOME` environment variable is set to a correct JDK:</summary>

  * Linux/Mac:
    ```shell
     JAVA_HOME="/home/$USER/.jdks/azul-17.0.14/"
     # Test with:
     ./gradlew --version
    ```
  * <details><summary>Windows:</summary>

    Not sure if any equivalent for Windows is needed, or it will "just work"? 
    </details>
  </details>
* <details><summary>Run the following command:</summary>

  * Linux/Mac

    ```shell
     ./gradlew gitPackage
    ```
  * <details><summary>Windows:</summary>

    ```shell
     # Same as for Linux?
    ```
    </details>
  </details>
* Now you can use `stelnet-{version}.zip` archive from the project root directory and unpack it to Starsector's `mods/` directory.  
  ___Note that you need to remove all Stelnet copies from the `mods/` directory before unpacking!___
