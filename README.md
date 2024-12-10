# college-admin-app
CSE 218 Final Project

## Introduction
This project is intended to be used by colleges to auto assign Instructors to Sections based upon seniority, section preference, day preference, and time segment preference. It allows for manual override to assign an Instructor to a section or replace Instructors. Certain data structures are used based on the needs to insert/delete/lookup often or sort.

## Features
- Automatically assigns instructors to sections based on preferences and seniority.
- Manual override feature for assigning or replacing instructors.
- Supports efficient handling of data with various optimized data structures.
- Provides views of sections and instructors that can be sorted.
- Allows for customization in `SchoolConfiguration` unique to a school added.


## Usage
- Launch the app and log in as a college's acronym
- Navigate to the "Instructor Preferences" section to input preferences.
- Use the "Auto-Assign" button to assign instructors based on input preferences.
- Optionally, override assignments manually in the "Manual Edit" tab.
## Data Structures Used

### 1. **LinkedHashMap**
- **Why** : Allows for efficient lookup and assignment of preferences for Instructors.
- **Usage** : `InstructorContainer`
- **Justification**: Its fixed size is justified as the College can cap instructor hires in the `SchoolSettings` enum based on budget constraints. This ensures constant time retrievals and updates while preserving insertion order for consistent processing.

### 2. **HashMap**
- **Why** : Also allows for efficient lookup and assignment by keys.
- **Usage** : `dayCheckBoxes`, `timeSegmentsCheckBoxes`
- **Justification**: This structure handles mapping days and time segments to their respective checkboxes efficiently. `HashMap` ensures constant-time performance for frequent retrieval and updates, making it ideal where lookups and updates occur frequently. It also avoids the overhead of maintaining order, which is unnecessary for these tasks.

### 3. **HashSet**
- **Why** : Ensures uniqueness and provides fast lookup for elements.
- **Usages** : `preferredCRNs`, `assignedCRNs` 
- **Justification**: Used to store collections of unique CRN values. `HashSet` is ideal here as it prevents duplicates and offers constant-time performance for basic operations like add, remove, and contains, making it suitable for tracking preferred or already-assigned sections.

### 4. **TreeMap**
- **Why** : Allows for uniqueness of an unknown quantity of elements without restraint and logarithmic lookup.
- **Usages** : `SectionsContainer`, `CourseContainer`, `StudentContainer`
- **Justification** : Sorted by unique keys such as Course Number, CRN Number, or ID number, allows for unbounded addition and relatively fast lookup. 

### 5. **TreeSet**
- **Why** : Ensures uniqueness of elements while maintaining their natural order and limitless addition in case of the need to add another student later on.
- **Usages** : `studentIDs`
- **Justification**: Ideal for storing unique student IDs and ensuring they are automatically sorted. This reduces the need for manual sorting and ensures efficient retrieval of elements in sorted order.

### 6. **ArrayList**
- **Why** : Allows for random element access, a settable preset size, and ability to use Collections sorting.
- **Usages**: `FIRST_NAMES`, `LAST_NAMES`, `instructorsToSort`
- **Justification** : `ArrayList` provides efficient element access by index and works seamlessly with sorting utilities in the `Collections` framework. Uses are set with an already pre-existing size, avoiding resizing.

### 7. **EnumSet**
- **Why**: Optimized for storing a small set of enum values.
- **Usages**: `preferredDays`, `preferredTimeSegments`
- **Justification**: Provides a high-performance, compact set implementation for storing day and time segment preferences. `EnumSet` is more efficient than other Set implementations for this purpose as it is specifically designed for use with enums.

## Future Possible Enhancements
- Allow for the manipulation and assignment of Students