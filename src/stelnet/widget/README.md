# Reusable UI widgets

## Heading With Buttons

An abstract class with a bunch of protected methods to create a rich section heading with additional buttons that are
positioned at the end of it.
Start with `renderQueryHeading` method, and add the first button using `renderFirstButton` method.
Any additional buttons can be added using `renderNextButton` method.

## Viewer

A tabbed market viewer that displays cargo and ships according to a specified grouping strategy. These include:

-   InMarket - split by submarkets content of a given market,
-   PerMarket - split by market all content of player storages, and
-   Unified - group all content of player storage.

Inside each tab content renderer picks appropriate rendering method.
