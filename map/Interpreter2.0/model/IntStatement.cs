namespace models
{
    public interface IntStatement
    {
        ProgState execute(ProgState state);
    }
}