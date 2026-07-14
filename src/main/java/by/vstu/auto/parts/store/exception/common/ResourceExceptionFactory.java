package by.vstu.auto.parts.store.exception.common;

public class ResourceExceptionFactory {
    public static ResourceNotFoundException CategoryNotFoundException(Long id){
        return new ResourceNotFoundException(id, "Category id = %s not found".formatted(id));
    }

    public static ResourceNotFoundException PartNotFoundException(Long id){
        return new ResourceNotFoundException(id, "Part id = %s not found".formatted(id));
    }

    public static ResourceNotFoundException BrandNotFoundException(Long id){
        return new ResourceNotFoundException(id, "Brand id = %s not found".formatted(id));
    }
}
