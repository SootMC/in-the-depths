# in-the-depths
so it lets you do a couple things ill go over it in sections

# Depth Crystals
Depth crystals are an item that has a 1/250 (configurable) chance to drop when mining a diamond or deepslate diamond ore.
They work off of the blockdropitem event, basically if a block drops a diamond and its not a container then it rolls for chance to spawn the depth crystal item entity.
The item stack of a crystal is created somewhere at the top of the plugin and then is fleshed out in the giveCrystal method, which also spawns the item entity. 

# "Crafting" System
the crystalCraft method is a PlayerSwapHandItemsEvent, theres a really big if statement at the top that basically just checks if theres a pickaxe in your main hand,
and a crystal, specifically a crystal and not just an echo shard, in your off hand (technically theyre swapped in code but thats because the event checks the items post
swap.) then there are a couple statements basically checking which item the player is trying to upgrade, if its a tool, armor piece, or sword. inside each of those checks
is one more check seeing if the tool is already upgraded, if it is, then it tells the player its already got a crystal in it and cancels the event. if it doesnt, then it runs the
code to upgrade the enchatment, subtract the stack of crystals the player is holding by 1, sets a lil lore bit at the bottom saying it has a crystal, and swaps the items
back to their initial positions. if the item is either unenchanted netherite or any other item, it gives a lil action bar message saying the item is inert, and cannot acccept
a crystal

# the random methods near the bottom of the plugin
those are as i said above just a bunch of item checks compiled into one set so i dont gotta be like "is it netherite chestplate or netherite legs or netherite boots" ya feel me
i plan on making the sword one not just a sword one but i wanted to keep using that style of doing it

# misc
beyond that its just the jackpot (the number the random method has to hit to spawn the crystal) and the chance number, chance number formula is (1/chance) chance of dropping
from diamond ore
