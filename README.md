# HOCON config printer

Do you have HOCON config files, that include other config files, which include 
other config files, and do you find it hard to see what is the final value of a
configuration key, and which file it comes from?

_Well, then, you're in luck! Introducing.._ HOCON config printer!!

Parses a HOCON config file, merges all the settings from all included files, 
   and writes the output to stdout.

It also shows from which file a setting was taken from (in verbose mode; useful
for debugging).

Default output: concise:  JSON (almost) representation, no comments.

## Options

-verbose: add comments that indicate how settings were merged

-resolve: Representation can contain substitutions, ex: ${VARIABLE}, which isn't
  JSON compliant. Using -resolve will resolve all substitutions with env vars 
  (and remove the keys entirely where the values are unresolved).
  Maybe not the most useful option, because of the key removal thingie.
  
# Installing

Clone this repository / download locally [as zip](https://github.com/vlad2/hocon-config-printer/archive/master.zip).

Run `./install.sh`

That's it, you can now go into any directory and run `hocon-config-printer`


## Technical info

The project is a small wrapper around the [HOCON library](https://github.com/lightbend/config).

### Authors

_Vlad Dinulescu <vlad.dinulescu@arnia.ro>_
