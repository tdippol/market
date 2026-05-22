#!/bin/bash
# Script to process renamed (R) and deleted (D) files from changed_files.txt
# For each R, if the old file exists in main, mv it to the new path
# For each D, if the file exists in main, rm it
# Logs all actions to process_renamed_deleted_files.log

SRC_DIR="/home/marco/development/mui"
DEST_DIR="/home/marco/development/temp"
CHANGED_FILE_LIST="$DEST_DIR/changed_files.txt"
LOG_FILE="$DEST_DIR/process_renamed_deleted_files.log"

cd "$SRC_DIR" || exit 1

echo "--- $(date) Starting rename/delete script ---" >> "$LOG_FILE"

git checkout main &>/dev/null

while IFS= read -r line; do
    # Renamed files: R<score>\told\tnew
    if [[ "$line" =~ ^R ]]; then
        old_file=$(echo "$line" | cut -f2)
        new_file=$(echo "$line" | cut -f3)
        if [ -f "$old_file" ]; then
            mkdir -p "$(dirname "$new_file")"
            mv "$old_file" "$new_file"
            echo "Renamed: $old_file -> $new_file" >> "$LOG_FILE"
        else
            echo "Rename skipped (not found): $old_file" >> "$LOG_FILE"
        fi
    fi
    # Deleted files: D\tfile
    if [[ "$line" =~ ^D ]]; then
        file=$(echo "$line" | cut -f2)
        if [ -f "$file" ]; then
            rm "$file"
            echo "Deleted: $file" >> "$LOG_FILE"
        else
            echo "Delete skipped (not found): $file" >> "$LOG_FILE"
        fi
    fi
done < "$CHANGED_FILE_LIST"

git checkout - &>/dev/null

echo "--- $(date) Rename/delete script finished ---" >> "$LOG_FILE"
