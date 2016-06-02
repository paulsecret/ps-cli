#!/bin/bash

####
# CONSTANTS
####
PROJECT="git@github.com:paulsecret/ps-cli.git"
CLI_DIR="${HOME}/.otcli"
BASHRC="${HOME}/.bashrc"
ZSHRC="${HOME}/.zshrc"
ALIAS="alias otcli=\"${HOME}/.otcli/cli.groovy\""
TEST="${HOME}/test.txt"

####
# FUNCTIONS
####
function validateCommand {
	command -v $1 > /dev/null 2>&1 || { echo >&2 "Command '$1' is not installed. Aborting."; exit 1; }
}

####
# EXEC
####

echo "Looking for a previous installation of O(t)CLI..."
if [ -d "$CLI_DIR" ]; then
	echo ""
	echo "======================================================================================================"
	echo " You already have O(t)CLI installed."
	echo " O(t)CLI was found at: ${CLI_DIR}"
	echo "======================================================================================================"
	echo ""
	exit 0
fi

# validate required tools
validateCommand git
validateCommand groovy
# Create directory structure
echo "Creating directories..."
mkdir -p $CLI_DIR && git clone $PROJECT $CLI_DIR && chmod 700 $CLI_DIR/cli.groovy
# Create alias for bash shell
if [ -e $BASHRC ] && ! grep -q "$ALIAS" "$BASHRC"; then 
	echo "updating ${BASHRC}..."
	echo -e "\n" >> "$BASHRC"
	echo $ALIAS >> "$BASHRC"
	source "$BASHRC"
fi
# Create alias for zshell
if [ -e $ZSHRC ] && ! grep -q "$ALIAS" "$ZSHRC"; then 
	echo "updating ${ZSHRC}..."
	echo -e "\n" >> "$ZSHRC"
	echo $ALIAS >> "$ZSHRC"
	source "$ZSHRC"
fi

echo ""
echo "======================================================================================================"
echo " Installation finished. Restart terminal to use cli command "
echo " 666 THE END 666 "
echo "======================================================================================================"
echo ""
