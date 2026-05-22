#!/bin/bash
# Script to copy files from /home/marco/development/temp back into the project workspace, preserving directory structure
# Logs all actions to restore_copied_files.log

SRC_DIR="/home/marco/development/temp"
DEST_DIR="/home/marco/development/mui"
CHANGED_FILE_LIST="$SRC_DIR/changed_files.txt"
LOG_FILE="$SRC_DIR/restore_copied_files.log"

cd "$SRC_DIR" || exit 1

echo "--- $(date) Starting restore script ---" >> "$LOG_FILE"

while IFS= read -r line; do
    # Only process lines starting with A or M (space or tab separated)
    if [[ "$line" =~ ^[AM][[:space:]]+ ]]; then
        file=$(echo "$line" | awk '{print $2}')
        src_path="$SRC_DIR/$file"
        dest_path="$DEST_DIR/$file"
        if [ -f "$src_path" ]; then
            mkdir -p "$(dirname "$dest_path")"
            cp "$src_path" "$dest_path"
            echo "Restored: $file" >> "$LOG_FILE"
        else
            echo "Missing in temp: $file (not restored)" >> "$LOG_FILE"
        fi
    fi
done < "$CHANGED_FILE_LIST"

echo "--- $(date) Restore script finished ---" >> "$LOG_FILE"
