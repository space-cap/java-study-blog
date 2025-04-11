package ch08;

class InstallException extends Exception {
    public InstallException(String message) {
        super(message);
    }
}

class SpaceException extends Exception {
    SpaceException(String message) {
        super(message);
    }
}

class MemoryException extends Exception {
    MemoryException(String message) {
        super(message);
    }
}

public class ChainedExceptionEx {
    public static void main(String[] args) {
        System.out.println("ChainedExceptionEx");

        try {
            install();
        } catch (InstallException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void startInstall() throws SpaceException, MemoryException {
        if (!enoughSpace()) {
            throw new SpaceException("설치할 공간이 부족합니다.");
        }

        if (!enoughMemory()) {
            throw new MemoryException("메모리가 부족합니다.");
        }
    }

    static void copyFiles() {}
    static void deleteTempFiles() {}

    static boolean enoughSpace() {
        return false; // Simulate not enough space
    }

    static boolean enoughMemory() {
        return true; // Simulate enough memory
    }

    static void install() throws InstallException {
        try {
            startInstall();
            copyFiles();
        } catch (SpaceException e) {
            InstallException ie = new InstallException("설치 중 예외 발생");
            ie.initCause(e); // Set the cause of the exception
            throw ie; // Throw the new exception
        } catch (MemoryException me) {
            InstallException ie = new InstallException("설치 중 예외 발생");
            ie.initCause(me); // Set the cause of the exception
            throw ie; // Throw the new exception
        } finally {
            deleteTempFiles();
        }
    }
}
