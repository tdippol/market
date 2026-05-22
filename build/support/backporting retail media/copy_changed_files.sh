#!/bin/bash
# Script to copy added/modified files from changed_files.txt to /home/marco/development/temp, preserving directory structure
# Logs all actions to copy_changed_files.log

SRC_DIR="/home/marco/development/mui"
DEST_DIR="/home/marco/development/temp"
CHANGED_FILE_LIST="$DEST_DIR/changed_files.txt"
LOG_FILE="$DEST_DIR/copy_changed_files.log"

cd "$SRC_DIR" || exit 1

echo "--- $(date) Starting copy script ---" >> "$LOG_FILE"

while IFS= read -r line; do
    # Only process lines starting with A or M (space or tab separated)
    if [[ "$line" =~ ^[AM][[:space:]]+ ]]; then
        file=$(echo "$line" | awk '{print $2}')
        dest_path="$DEST_DIR/$file"
        mkdir -p "$(dirname "$dest_path")"
        if [ -f "$file" ]; then
            cp --parents "$file" "$DEST_DIR" 2>/dev/null || cp "$file" "$dest_path"
            echo "Copied: $file" >> "$LOG_FILE"
        else
            echo "Missing: $file (not copied)" >> "$LOG_FILE"
        fi
    fi
done < "$CHANGED_FILE_LIST"

echo "--- $(date) Copy script finished ---" >> "$LOG_FILE"
