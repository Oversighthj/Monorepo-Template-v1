#!/usr/bin/env python3
import os, sys

IGNORE = {
    '.git', '.idea', '.vscode', 'node_modules', 'target', 'build', '.dart_tool',
    '__pycache__', '.mvn', 'venv', '.backups'
}

BASE = None

def generate_tree(root: str, prefix: str = ""):
    entries = [e for e in os.listdir(root) if e not in IGNORE]
    entries.sort()
    count_dirs = 0
    count_files = 0
    for i, name in enumerate(entries):
        full = os.path.join(root, name)
        rel = os.path.relpath(full, BASE)
        connector = '└── ' if i == len(entries) - 1 else '├── '
        print(prefix + connector + f'./{rel}')
        if os.path.isdir(full):
            count_dirs += 1
            next_prefix = prefix + ('    ' if i == len(entries) - 1 else '│   ')
            d, f = generate_tree(full, next_prefix)
            count_dirs += d
            count_files += f
        else:
            count_files += 1
    return count_dirs, count_files


def main():
    path = sys.argv[1] if len(sys.argv) > 1 else '.'
    global BASE
    BASE = os.path.abspath(path)
    print('.')
    dirs, files = generate_tree(BASE)
    print(f"\n{dirs} directories, {files} files")

if __name__ == '__main__':
    main()
